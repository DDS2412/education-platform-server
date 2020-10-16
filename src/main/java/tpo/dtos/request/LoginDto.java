package tpo.dtos.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginDto {
    private String login;

    private String password;
}
