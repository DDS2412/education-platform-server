package tpo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tpo.configurations.EducationPlatformConfiguration;
import tpo.dtos.json.TestJsonDto;
import tpo.dtos.json.UserJsonDto;
import tpo.services.TestService;
import tpo.services.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(value = {EducationPlatformConfiguration.class})
public class EducationPlatformServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EducationPlatformServerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(TestService testService, UserService userService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<TestJsonDto>> testTypeReference = new TypeReference<>(){};
            TypeReference<List<UserJsonDto>> userTypeReference = new TypeReference<>(){};

            try {
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tests.json");
                List<TestJsonDto> testJsonDtos = mapper.readValue(inputStream, testTypeReference);
                testService.addTestsToDB(testJsonDtos);

                System.out.println("Тесты успешно добавлены в базу данных!");

                inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
                List<UserJsonDto> userJsonDtos = mapper.readValue(inputStream, userTypeReference);
                userService.addUsersToDB(userJsonDtos);

                System.out.println("Пользователи успешно добавлены в базу данных!");
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        };
    }
}
