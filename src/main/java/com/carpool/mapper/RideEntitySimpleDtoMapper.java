package com.carpool.mapper;

import org.mapstruct.Mapper;

import com.carpool.dto.SimpleRideDto;
import com.carpool.entity.RideEntity;

@Mapper(componentModel="spring", uses= {UserEntitySimpleDtoMapper.class})
public interface RideEntitySimpleDtoMapper {
	SimpleRideDto toDto(RideEntity entity);

	RideEntity toEntity(SimpleRideDto dto);
}
