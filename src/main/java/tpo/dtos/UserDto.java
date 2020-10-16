package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserDto {
    private Integer id;

    private String Name;

    private String Type;

    private List<ChatDto> chats;
}
