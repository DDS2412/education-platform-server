package tpo.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.dtos.request.LoginDto;
import tpo.dtos.response.AuthResultDto;
import tpo.services.UserService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(allowCredentials = "true")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Optional<AuthResultDto>> login(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}