package com.carpool.mapper;

import com.carpool.dto.RideDto;
import com.carpool.entity.RideEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressEntityDtoMapper.class, UserEntitySimpleDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RideEntityDtoMapper {

    RideDto toDto(RideEntity rideEntity);

    RideEntity toEntity(RideDto rideDto);
}
