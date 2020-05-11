package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;
import tpo.dtos.AnswerDto;

import java.util.List;

@Data
@Accessors(chain = true)
public class TaskDto {
    private Integer id;

    private String question;

    private List<AnswerDto> answers;

    private Integer correctAnswer;

    private Double score;
}
