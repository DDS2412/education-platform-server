package tpo.services.impl;

import org.springframework.stereotype.Service;
import tpo.domains.User;
import tpo.dtos.json.UserJsonDto;
import tpo.dtos.request.LoginDto;
import tpo.dtos.response.AuthResultDto;
import tpo.mappers.json.UserJsonMapper;
import tpo.repositories.UserRepository;
import tpo.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserJsonMapper userJsonMapper;

    public UserServiceImpl(UserRepository userRepository, UserJsonMapper userJsonMapper) {
        this.userRepository = userRepository;
        this.userJsonMapper = userJsonMapper;
    }

    @Override
    public Optional<AuthResultDto> login(LoginDto loginDto) {
        Optional<User> userByLogin = userRepository.getFirstByLogin(loginDto.getLogin());
        AuthResultDto resultDto = null;

        if (userByLogin.isPresent()){
            User user = userByLogin.get();
            if (user.getPassword().equals(loginDto.getPassword())){
                String token = UUID.randomUUID().toString();
                user.setToken(token);

                userRepository.save(user);
                resultDto = new AuthResultDto()
                        .setLogin(user.getLogin())
                        .setType(user.getType())
                        .setToken(token);
            }
        }

        return Optional.ofNullable(resultDto);
    }

    @Override
    public void addUsersToDB(List<UserJsonDto> usersJsonDto) {
        List<User> users = usersJsonDto
                .stream()
                .map(userJsonMapper::toEntity)
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
