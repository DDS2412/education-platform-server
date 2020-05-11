package tpo.dtos.json;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TaskJsonDto {
    private String question;

    private List<AnswerJsonDto> answers;

    private Integer correctAnswer;

    private Double score;
}
