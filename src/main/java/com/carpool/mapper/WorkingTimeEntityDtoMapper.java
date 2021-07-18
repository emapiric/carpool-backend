package com.carpool.mapper;

import com.carpool.dto.WorkingTimeDto;
import com.carpool.entity.WorkingTimeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface WorkingTimeEntityDtoMapper {

    WorkingTimeDto toDto(WorkingTimeEntity workingTimeEntity);

    WorkingTimeEntity toEntity(WorkingTimeDto workingTimeDto);
}
