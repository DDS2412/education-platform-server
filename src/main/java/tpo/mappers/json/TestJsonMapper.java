package tpo.mappers.json;

import org.mapstruct.Mapper;
import tpo.domains.Test;
import tpo.dtos.json.TestJsonDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {TaskJsonMapper.class}, config = MappingConfig.class)
public interface TestJsonMapper extends TwoWayMapper<TestJsonDto, Test> {
}
