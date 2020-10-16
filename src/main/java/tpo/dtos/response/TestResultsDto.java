package tpo.dtos.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestResultsDto {
    private Integer testId;

    private String testName;

    private Double maxScore;

    private Double userScore;
}
