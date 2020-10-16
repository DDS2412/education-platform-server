package tpo.services;

import tpo.dtos.json.TestJsonDto;
import tpo.dtos.TestDto;
import tpo.dtos.simplified.SimplifiedTestDto;

import java.util.List;

public interface TestService {
    void addTestsToDB(List<TestJsonDto> testJsonDtos);

    List<SimplifiedTestDto> getAllTests();

    TestDto getTestById(Integer id);

    String getTestTitleById(Integer testId);
}
