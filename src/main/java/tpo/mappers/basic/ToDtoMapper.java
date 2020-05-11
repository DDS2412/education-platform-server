package tpo.mappers.basic;

import tpo.domains.Identifiable;

public interface ToDtoMapper<DTO, E extends Identifiable> extends MappingConfig {
    DTO toDto(E entity);
}