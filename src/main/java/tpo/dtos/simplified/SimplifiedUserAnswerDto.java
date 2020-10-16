package tpo.dtos.simplified;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class SimplifiedUserAnswerDto {
    private Integer id;

    private String textAnswer;

    private Integer numberOfAnswer;
}
