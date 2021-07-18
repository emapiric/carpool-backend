package com.carpool.mapper;

import com.carpool.dto.SimpleRatingDto;
import com.carpool.entity.RatingEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserEntitySimpleDtoMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RatingEntityDtoMapper {

    SimpleRatingDto toDto(RatingEntity ratingEntity);

    RatingEntity toEntity(SimpleRatingDto ratingDto);
}
