package tpo.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tpo.dtos.TestDto;
import tpo.dtos.simplified.SimplifiedTestDto;
import tpo.services.TestService;

import java.util.List;

@RestController
@RequestMapping(value = "/tests", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestsController {
    private final TestService testService;

    public TestsController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SimplifiedTestDto>> getAllTests(){
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping(value = "/")
    public ResponseEntity<TestDto> getTestById(@RequestParam("test_id") Integer testId){
        return ResponseEntity.ok(testService.getTestById(testId));
    }
}
