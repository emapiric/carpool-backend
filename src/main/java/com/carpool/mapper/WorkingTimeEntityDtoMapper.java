package com.carpool.mapper;

import org.mapstruct.Mapper;

import com.carpool.dto.WorkingTimeDto;
import com.carpool.entity.WorkingTimeEntity;

@Mapper(componentModel="spring")
public interface WorkingTimeEntityDtoMapper {

    WorkingTimeDto toDto(WorkingTimeEntity workingTimeEntity);

    WorkingTimeEntity toEntity(WorkingTimeDto workingTimeDto);
}
