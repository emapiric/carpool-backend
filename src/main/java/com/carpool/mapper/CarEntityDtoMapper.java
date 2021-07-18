package com.carpool.mapper;

import com.carpool.dto.CarDto;
import com.carpool.entity.CarEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserEntitySimpleDtoMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarEntityDtoMapper {

    CarDto toDto(CarEntity carEntity);

    CarEntity toEntity(CarDto carDto);
}
