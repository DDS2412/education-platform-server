package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ChatDto {
    private Integer id;

    private String name;

    private List<MessageDto> messages;

    private List<UserDto> group;
}
