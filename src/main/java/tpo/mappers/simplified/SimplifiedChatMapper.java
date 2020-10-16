package tpo.mappers.simplified;

import org.mapstruct.Mapper;
import tpo.domains.Chat;
import tpo.dtos.simplified.SimplifiedChatDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(config = MappingConfig.class)
public interface SimplifiedChatMapper extends TwoWayMapper<SimplifiedChatDto, Chat> {
}
