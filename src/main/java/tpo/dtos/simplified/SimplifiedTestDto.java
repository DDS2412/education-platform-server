package tpo.dtos.simplified;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimplifiedTestDto {
    private Integer id;

    private String testName;

    private Integer testUniqNumber;
}
