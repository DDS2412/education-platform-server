package tpo.services.impl;

import org.springframework.stereotype.Service;
import tpo.domains.Answer;
import tpo.domains.Task;
import tpo.domains.Test;
import tpo.dtos.TestDto;
import tpo.dtos.json.TestJsonDto;
import tpo.dtos.simplified.SimplifiedTestDto;
import tpo.mappers.json.TestJsonMapper;
import tpo.mappers.TestMapper;
import tpo.mappers.simplified.SimplifiedTestMapper;
import tpo.repositories.TestRepository;
import tpo.services.TestService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    private final TestJsonMapper testJsonMapper;
    private final TestMapper testMapper;
    private final SimplifiedTestMapper simplifiedTestMapper;

    public TestServiceImpl(TestRepository testRepository,
                           TestJsonMapper testJsonMapper,
                           TestMapper testMapper,
                           SimplifiedTestMapper simplifiedTestMapper) {

        this.testRepository = testRepository;
        this.testJsonMapper = testJsonMapper;
        this.testMapper = testMapper;
        this.simplifiedTestMapper = simplifiedTestMapper;
    }

    @Override
    public void addTestsToDB(List<TestJsonDto> testJsonDtos) {
        List<Test> tests = testJsonDtos.stream().map(testJsonMapper::toEntity).collect(Collectors.toList());

        for (Test test : tests){
            for(Task task : test.getTasks()){
                task
                        .setTest(test)
                        .getAnswers()
                        .forEach(answer -> answer.setTask(task));
            }
        }

        testRepository.saveAll(tests);
    }

    @Override
    public List<SimplifiedTestDto> getAllTests() {
        return testRepository
                .findAll()
                .stream()
                .map(simplifiedTestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestDto getTestById(Integer id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        return optionalTest.map(testMapper::toDto).orElse(null);
    }

    public String getTestTitleById(Integer testId) {
        Optional<Test> optionalTest = testRepository.findById(testId);
        return optionalTest.map(Test::getTestName).orElse(null);
    }
}
