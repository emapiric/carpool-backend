package com.carpool.mapper;

import org.mapstruct.Mapper;

import com.carpool.dto.TakenRideDto;
import com.carpool.entity.TakenRideEntity;

@Mapper(componentModel="spring", uses= {UserEntitySimpleDtoMapper.class, RideEntitySimpleDtoMapper.class})
public interface TakenRideEntityDtoMapper {
	
	TakenRideDto toDto(TakenRideEntity takenRideEntity);

	TakenRideEntity toEntity(TakenRideDto takenRideDto);
	

}
