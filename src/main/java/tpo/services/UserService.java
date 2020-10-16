package tpo.services;

import tpo.dtos.json.UserJsonDto;
import tpo.dtos.request.LoginDto;
import tpo.dtos.response.AuthResultDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<AuthResultDto> login(LoginDto loginDto);

    void addUsersToDB(List<UserJsonDto> usersJsonDto);
}
