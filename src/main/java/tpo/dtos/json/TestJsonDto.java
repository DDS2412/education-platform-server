package tpo.dtos.json;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TestJsonDto {
    private String testName;

    private Integer testUniqNumber;

    private List<TaskJsonDto> tasks;
}
