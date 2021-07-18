package com.carpool.mapper;

import com.carpool.dto.CityDto;
import com.carpool.entity.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CityEntityDtoMapper {

    CityDto toDto(CityEntity cityEntity);

    CityEntity toEntity(CityDto cityDto);
}
