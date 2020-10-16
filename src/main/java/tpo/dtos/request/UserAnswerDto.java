package tpo.dtos.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAnswerDto {
    private Integer userTaskId;

    private String userToken;

    private Integer userAnswer;
}
