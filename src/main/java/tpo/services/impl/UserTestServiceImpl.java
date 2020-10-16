package tpo.services.impl;

import org.springframework.stereotype.Service;
import tpo.domains.*;
import tpo.dtos.request.UserAnswerDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.response.TestResultsDto;
import tpo.dtos.simplified.SimplifiedUserAnswerDto;
import tpo.dtos.simplified.SimplifiedUserTaskDto;
import tpo.dtos.simplified.SimplifiedUserTestDto;
import tpo.repositories.TestRepository;
import tpo.repositories.UserRepository;
import tpo.repositories.UserTaskRepository;
import tpo.repositories.UserTestRepository;
import tpo.services.UserTestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTestServiceImpl implements UserTestService {
    private final UserTestRepository userTestRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final UserTaskRepository userTaskRepository;

    public UserTestServiceImpl(UserTestRepository userTestRepository,
                               TestRepository testRepository,
                               UserRepository userRepository,
                               UserTaskRepository userTaskRepository) {
        this.userTestRepository = userTestRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public ExecutionStatusDto assignTestOnUsers(List<User> filteredUsers, Integer testId) {
        Optional<Test> optionalTest = testRepository.findById(testId);
        if (optionalTest.isPresent()){
            Test test = optionalTest.get();

            for (User user : filteredUsers){
                try {
                    assignTestOnUser(user, test);
                } catch (Exception ex){
                    return ExecutionStatusDto
                            .createBadExecutionStatus("Произошла ошибка во время добавления одного из тестов");
                }
            }

            return ExecutionStatusDto.createOkExecutionStatus();
        }

        return ExecutionStatusDto.createBadExecutionStatus("Задан неверный идентификатор теста");
    }

    @Override
    public SimplifiedUserTestDto getTestById(Integer testId, String userToken) {
        Optional<User> optionalUser = userRepository.findByToken(userToken);

        if (optionalUser.isPresent()) {
            Optional<Test> optionalTest = testRepository.findById(testId);
            if (optionalTest.isPresent()) {
                Test test = optionalTest.get();
                User user = optionalUser.get();

                Optional<UserTest> optionalUserTest = userTestRepository.findByUserAndTest(user, test);
                if (optionalUserTest.isPresent()){
                    UserTest userTest = optionalUserTest.get();

                    SimplifiedUserTestDto simplifiedUserTestDto = new SimplifiedUserTestDto()
                            .setId(userTest.getId());

                    List<SimplifiedUserTaskDto> simplifiedUserTaskDtos = userTest
                            .getTasks()
                            .stream()
                            .map(userTask ->
                                    new SimplifiedUserTaskDto()
                                            .setId(userTask.getId())
                                            .setCorrectAnswer(userTask.getTask().getCorrectAnswer())
                                            .setQuestion(userTask.getTask().getQuestion())
                                            .setUserAnswerDtos(
                                                    userTask
                                                            .getTask()
                                                            .getAnswers()
                                                            .stream()
                                                            .map(answer ->
                                                                    new SimplifiedUserAnswerDto()
                                                                            .setId(answer.getId())
                                                                            .setNumberOfAnswer(answer.getNumberOfAnswer())
                                                                            .setTextAnswer(answer.getTextAnswer()))
                                                            .collect(Collectors.toList())))
                            .collect(Collectors.toList());

                    resetTest(simplifiedUserTaskDtos.stream().map(SimplifiedUserTaskDto::getId).collect(Collectors.toList()));

                    return simplifiedUserTestDto.setUserTaskDtos(simplifiedUserTaskDtos);
                }
            }

        }

        return null;
    }

    @Override
    public ExecutionStatusDto sendAnswer(UserAnswerDto userAnswerDto){
        Optional<User> optionalUser = userRepository.findByToken(userAnswerDto.getUserToken());

        if (optionalUser.isPresent()) {
            Optional<UserTask> optionalUserTask = userTaskRepository.findById(userAnswerDto.getUserTaskId());
            if(optionalUserTask.isPresent()){
                UserTask userTask = optionalUserTask.get();
                Task task = userTask.getTask();

                userTask.setUserAnswer(userAnswerDto.getUserAnswer());

                if (task.getCorrectAnswer().equals(userAnswerDto.getUserAnswer())){
                    userTask.setScore(task.getMaxScore());
                } else {
                    userTask.setScore(0.0);
                }

                userTaskRepository.save(userTask);

                return ExecutionStatusDto.createOkExecutionStatus();
            }
            return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный идентификатор задачи!");
        }

        return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный токен!");
    }

    @Override
    public List<TestResultsDto> getTestsResults(String userToken) {
        Optional<User> optionalUser = userRepository.findByToken(userToken);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            List<TestResultsDto> testResultsDtos = new ArrayList<>();

            List<UserTest> userTests = testRepository
                    .findAll()
                    .stream()
                    .map(test -> userTestRepository.findByUserAndTest(user, test))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            if (user.getLogin().equals("student")){
                System.out.println();
            }
            for (UserTest userTest : userTests){
                testResultsDtos.add(
                        new TestResultsDto()
                                .setTestId(userTest.getId())
                                .setTestName(userTest.getTest().getTestName())
                                .setMaxScore(getMaxScore(userTest))
                                .setUserScore(getUserScore(userTest)));
            }

            return testResultsDtos;
        }

        return null;
    }

    private Double getMaxScore(UserTest userTest){
        Double maxScore = 0.0;
        for (UserTask userTask : userTest.getTasks()){
            maxScore += userTask.getTask().getMaxScore();
        }

        return maxScore;
    }

    private Double getUserScore(UserTest userTest){
        Double userScore = 0.0;
        for (UserTask userTask : userTest.getTasks()){
            if (userTask.getScore() == null){
                break;
            }

            userScore += userTask.getScore();
        }

        return userScore;
    }

    private void resetTest(List<Integer> userTaskIds){
        List<UserTask> tasks = userTaskRepository.findAllById(userTaskIds);

        tasks.forEach(task -> task.setScore(0.0));

        userTaskRepository.saveAll(tasks);
    }

    private void assignTestOnUser(User user, Test test){
        List<Task> tasks = test.getTasks();

        UserTest userTest = new UserTest()
                .setTest(test)
                .setUser(user);

        List<UserTask> userTasks = tasks
                .stream()
                .map(task -> new UserTask().setTask(task).setTest(userTest).setScore(0.0))
                .collect(Collectors.toList());

        userTest.setTasks(userTasks);

        userTestRepository.save(userTest);
    }
}
