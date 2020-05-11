package tpo.mappers.simplified;

import org.mapstruct.Mapper;
import tpo.domains.Test;
import tpo.dtos.simplified.SimplifiedTestDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(config = MappingConfig.class)
public interface SimplifiedTestMapper extends TwoWayMapper<SimplifiedTestDto, Test> {
}
