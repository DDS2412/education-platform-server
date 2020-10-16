package tpo.dtos.simplified;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SimplifiedUserTestDto {
    private Integer id;

    private List<SimplifiedUserTaskDto> userTaskDtos;
}
