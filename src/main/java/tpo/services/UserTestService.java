package tpo.services;

import tpo.domains.User;
import tpo.dtos.request.UserAnswerDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.response.TestResultsDto;
import tpo.dtos.simplified.SimplifiedUserTestDto;

import java.util.List;

public interface UserTestService {
    ExecutionStatusDto assignTestOnUsers(List<User> filteredUsers, Integer testId);

    SimplifiedUserTestDto getTestById(Integer testId, String userToken);

    ExecutionStatusDto sendAnswer(UserAnswerDto userAnswerDto);

    List<TestResultsDto> getTestsResults(String userToken);
}
