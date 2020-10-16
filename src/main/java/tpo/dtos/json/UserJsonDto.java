package tpo.dtos.json;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserJsonDto {
    private String login;

    private String password;

    private String type;
}
