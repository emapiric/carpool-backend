package com.carpool.mapper;

import com.carpool.dto.SimpleUserDto;
import com.carpool.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntitySimpleDtoMapper {

    SimpleUserDto toDto(UserEntity userEntity);

    UserEntity toEntity(SimpleUserDto userDto);
}
