package tpo.mappers.json;

import org.mapstruct.Mapper;
import tpo.domains.Test;
import tpo.domains.User;
import tpo.dtos.json.UserJsonDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(config = MappingConfig.class)
public interface UserJsonMapper extends TwoWayMapper<UserJsonDto, User> {
}
