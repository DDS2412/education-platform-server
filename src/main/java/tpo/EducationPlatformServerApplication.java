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
import tpo.services.TestService;

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
    CommandLineRunner runner(TestService testService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<TestJsonDto>> typeReference = new TypeReference<List<TestJsonDto>>(){};

            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tests.json");
            try {
                List<TestJsonDto> testJsonDtos = mapper.readValue(inputStream, typeReference);
                testService.addTestsToDB(testJsonDtos);

                System.out.println("Тесты успешно добавлены в базу данных!");
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        };
    }
}
