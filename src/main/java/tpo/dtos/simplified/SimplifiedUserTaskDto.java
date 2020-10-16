package tpo.dtos.simplified;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.List;

@Data
@Accessors(chain = true)
public class SimplifiedUserTaskDto {
    private Integer id;

    private String question;

    private Integer correctAnswer;

    private List<SimplifiedUserAnswerDto> userAnswerDtos;
}
