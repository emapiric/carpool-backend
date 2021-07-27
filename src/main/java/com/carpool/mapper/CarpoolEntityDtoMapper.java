package com.carpool.mapper;

import com.carpool.dto.CarpoolDto;
import com.carpool.dto.SimpleRideDto;
import com.carpool.entity.CarpoolEntity;
import com.carpool.entity.RideEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RideEntitySimpleDtoMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarpoolEntityDtoMapper {
    CarpoolDto toDto(CarpoolEntity rideEntity);

    CarpoolEntity toEntity(CarpoolDto rideDto);
}
