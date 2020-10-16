package tpo.dtos.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewMessageDto {
    private Integer chatId;

    private String text;

    private String userToken;
}
