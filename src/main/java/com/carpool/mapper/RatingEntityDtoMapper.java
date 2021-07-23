package com.carpool.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.carpool.dto.RatingDto;
import com.carpool.entity.RatingEntity;

@Mapper(componentModel = "spring", uses = {UserEntitySimpleDtoMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RatingEntityDtoMapper {

	RatingDto toDto(RatingEntity ratingEntity);

    RatingEntity toEntity(RatingDto ratingDto);
}
