package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.Task;
import tpo.dtos.TaskDto;
import tpo.mappers.AnswerMapper;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {AnswerMapper.class}, config = MappingConfig.class)
public interface TaskMapper extends TwoWayMapper<TaskDto, Task> {
}
