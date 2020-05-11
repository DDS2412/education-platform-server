package tpo.mappers.json;

import org.mapstruct.Mapper;
import tpo.domains.Task;
import tpo.dtos.json.TaskJsonDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {AnswerJsonMapper.class}, config = MappingConfig.class)
public interface TaskJsonMapper extends TwoWayMapper<TaskJsonDto, Task> {
}
