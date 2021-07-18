package com.carpool.mapper;

import com.carpool.dto.UserDto;
import com.carpool.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring", uses = {
        AddressEntityDtoMapper.class, CarEntityDtoMapper.class, WorkingTimeEntityDtoMapper.class,
        RatingEntityDtoMapper.class, TakenRideEntityDtoMapper.class, RideEntityDtoMapper.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserEntityDtoMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);
}
