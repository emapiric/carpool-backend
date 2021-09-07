package com.carpool.mapper;


import com.carpool.dto.NotificationDto;
import com.carpool.entity.NotificationEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TakenRideEntityDtoMapper.class, UserEntityDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NotificationEntityDtoMapper {

    NotificationDto toDto(NotificationEntity notificationEntity);

    NotificationEntity toEntity(NotificationDto notificationDto);
}
