package tpo.dtos.json;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AnswerJsonDto {
    private String textAnswer;

    private Integer numberOfAnswer;
}
