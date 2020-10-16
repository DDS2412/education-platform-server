package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.Message;
import tpo.dtos.MessageDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {ChatMapper.class, UserMapper.class}, config = MappingConfig.class)
public interface MessageMapper extends TwoWayMapper<MessageDto, Message> {
}
