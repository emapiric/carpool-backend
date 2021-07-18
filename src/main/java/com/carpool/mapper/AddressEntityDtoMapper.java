package com.carpool.mapper;

import com.carpool.dto.AddressDto;
import com.carpool.entity.AddressEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CityEntityDtoMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressEntityDtoMapper {
    AddressDto toDto(AddressEntity addressEntity);

    AddressEntity toEntity(AddressDto addressDto);

}
