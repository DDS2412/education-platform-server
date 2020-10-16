package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MessageDto {
    private Integer id;

    private String text;

    private UserDto user_message;
}
