package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.Chat;
import tpo.dtos.ChatDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(uses = {UserMapper.class, MessageMapper.class}, config = MappingConfig.class)
public interface ChatMapper extends TwoWayMapper<ChatDto, Chat> {
}
