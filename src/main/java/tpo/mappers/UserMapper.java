package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.User;
import tpo.dtos.UserDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {ChatMapper.class} ,config = MappingConfig.class)
public interface UserMapper  extends TwoWayMapper<UserDto, User> {
}
