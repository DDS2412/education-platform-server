package tpo.dtos.simplified;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimplifiedChatDto {
    private Integer id;

    private String name;

    private Integer countOfUsers;
}
