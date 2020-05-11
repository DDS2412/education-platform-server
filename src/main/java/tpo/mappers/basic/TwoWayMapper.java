package tpo.mappers.basic;


import tpo.domains.Identifiable;

public interface TwoWayMapper<DTO, E extends Identifiable> extends ToDtoMapper<DTO, E>, ToEntityMapper<DTO, E> {
}
