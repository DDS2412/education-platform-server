package tpo.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.dtos.request.UserAnswerDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.response.TestResultsDto;
import tpo.dtos.simplified.SimplifiedTestDto;
import tpo.dtos.simplified.SimplifiedUserTestDto;
import tpo.services.TestService;
import tpo.services.UserTestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tests", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(allowCredentials = "true")
public class TestsController {
    private final TestService testService;
    private final UserTestService userTestService;

    public TestsController(TestService testService,
                           UserTestService userTestService) {
        this.testService = testService;
        this.userTestService = userTestService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SimplifiedTestDto>> getAllTests(){
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping(value = "/")
    public ResponseEntity<SimplifiedUserTestDto> getTestById(@RequestParam("test_id") Integer testId, @RequestParam("user_token") String userToken){
        return ResponseEntity.ok(userTestService.getTestById(testId, userToken));
    }

    @PostMapping(value = "/answer")
    public ResponseEntity<ExecutionStatusDto> sendAnswer(@RequestBody @Valid UserAnswerDto userAnswerDto) {
        return ResponseEntity.ok(userTestService.sendAnswer(userAnswerDto));
    }

    @GetMapping(value = "/results")
    public ResponseEntity<List<TestResultsDto>> getTestsResult(@RequestParam("user_token") String userToken){
        return ResponseEntity.ok(userTestService.getTestsResults(userToken));
    }
}