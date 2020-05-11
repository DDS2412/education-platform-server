package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TestDto {
    private Integer id;

    private String testName;

    private Integer testUniqNumber;

    private List<TaskDto> tasks;
}
