package com.carpool.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carpool.dto.RatingDto;
import com.carpool.dto.TakenRideDto;
import com.carpool.dto.UserDto;
import com.carpool.entity.RatingEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;

@Component
public class UserEntityDtoMapper {
	CarEntityDtoMapper carMapper;
	AddressEntityDtoMapper addressMapper;
	RatingEntityDtoMapper ratingMapper;
	UserEntitySimpleDtoMapper userMapper;
	RideEntitySimpleDtoMapper rideMapper;

	@Autowired
	public UserEntityDtoMapper(RideEntitySimpleDtoMapper rideMapper, UserEntitySimpleDtoMapper userMapper, CarEntityDtoMapper carMapper, AddressEntityDtoMapper addressMapper, RatingEntityDtoMapper ratingMapper) {
		this.carMapper = carMapper;
		this.addressMapper = addressMapper;
		this.ratingMapper = ratingMapper;
		this.userMapper = userMapper;
		this.rideMapper = rideMapper;
	}

	public UserDto toDto(UserEntity entity) {
		UserDto dto = new UserDto();
		dto.setCar(carMapper.toDto(entity.getCar()));
		dto.setEmail(entity.getEmail());
		dto.setFullName(entity.getFullName());
		dto.setId(entity.getId());
		dto.setPassword(entity.getPassword());
		dto.setPhone(entity.getPhone());
		dto.setUsername(entity.getUsername());
		List<RatingEntity> ratings = new ArrayList<>();
		dto.setRatings(ratings.stream().map(rating -> {
			return ratingMapper.toDto(rating);
		}).collect(Collectors.toList()));
		for (TakenRideEntity takenRide : entity.getTakenRides()) {
			dto.getTakenRides().add(new TakenRideDto(userMapper.toDto(entity),
					rideMapper.toDto(takenRide.getRide()), takenRide.isApproved()));
		}
		return dto;
	}

	public UserEntity toEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		entity.setCar(carMapper.toEntity(dto.getCar()));
		entity.setEmail(dto.getEmail());
		entity.setFullName(dto.getFullName());
		entity.setId(dto.getId());
		entity.setPassword(dto.getPassword());
		entity.setPhone(dto.getPhone());
		entity.setUsername(dto.getUsername());
		List<RatingDto> ratings = new ArrayList<>();
		entity.setRatings(ratings.stream().map(rating -> {
			return ratingMapper.toEntity(rating);
		}).collect(Collectors.toList()));
		for (TakenRideDto takenRide : dto.getTakenRides()) {
			entity.getTakenRides().add(new TakenRideEntity(entity,
					rideMapper.toEntity(takenRide.getRide()), takenRide.isApproved()));
		}
		return entity;

	}
}
