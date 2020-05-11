package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AnswerDto {
    private Integer id;

    private String textAnswer;

    private Integer numberOfAnswer;
}
