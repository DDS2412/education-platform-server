package tpo.dtos.request;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AssignTestDto {
    private Integer chatId;

    private Integer testId;

    private String userToken;
}