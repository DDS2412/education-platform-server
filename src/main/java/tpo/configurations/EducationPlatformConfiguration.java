package tpo.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("education")
public class EducationPlatformConfiguration {

    private String testPath;
}
