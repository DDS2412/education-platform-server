package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.Test;
import tpo.dtos.TestDto;
import tpo.mappers.TaskMapper;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {TaskMapper.class}, config = MappingConfig.class)
public interface TestMapper extends TwoWayMapper<TestDto, Test> {
}
