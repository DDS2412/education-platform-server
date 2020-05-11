package tpo.mappers;

import org.mapstruct.Mapper;
import tpo.domains.Answer;
import tpo.dtos.AnswerDto;
import tpo.mappers.basic.MappingConfig;
import tpo.mappers.basic.TwoWayMapper;

@Mapper(config = MappingConfig.class)
public interface AnswerMapper extends TwoWayMapper<AnswerDto, Answer> {
}
