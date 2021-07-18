package com.carpool.mapper;

import com.carpool.dto.TakenRideDto;
import com.carpool.entity.TakenRideEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserEntitySimpleDtoMapper.class, RideEntityDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TakenRideEntityDtoMapper {

    TakenRideDto toDto(TakenRideEntity takenRideEntity);

    TakenRideEntity toEntity(TakenRideDto takenRideDto);
}
