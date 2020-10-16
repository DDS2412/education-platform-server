package tpo.dtos.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthResultDto {
    private String login;

    private String type;

    private String token;
}
