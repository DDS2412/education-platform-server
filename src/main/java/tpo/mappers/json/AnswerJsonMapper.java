package tpo.mappers.json;

import org.mapstruct.Mapper;
import tpo.domains.Answer;
import tpo.dtos.json.AnswerJsonDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(config = MappingConfig.class)
public interface AnswerJsonMapper extends TwoWayMapper<AnswerJsonDto, Answer> {
}
