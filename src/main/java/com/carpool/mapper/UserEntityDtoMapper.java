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
	CarpoolEntityDtoMapper carpoolMapper;

	@Autowired
	public UserEntityDtoMapper(CarpoolEntityDtoMapper carpoolMapper,RideEntitySimpleDtoMapper rideMapper, UserEntitySimpleDtoMapper userMapper, CarEntityDtoMapper carMapper, AddressEntityDtoMapper addressMapper, RatingEntityDtoMapper ratingMapper) {
		this.carMapper = carMapper;
		this.addressMapper = addressMapper;
		this.ratingMapper = ratingMapper;
		this.userMapper = userMapper;
		this.rideMapper = rideMapper;
		this.carpoolMapper = carpoolMapper;
	}

	public UserDto toDto(UserEntity entity) {
		UserDto dto = new UserDto();
		dto.setCar(carMapper.toDto(entity.getCar()));
		dto.setEmail(entity.getEmail());
		dto.setFullName(entity.getFullName());
		dto.setId(entity.getId());
		dto.setPassword(entity.getPassword());
		dto.setPhone(entity.getPhone());
		dto.setHomeAddress(addressMapper.toDto(entity.getHomeAddress()));
		dto.setWorkAddress(addressMapper.toDto(entity.getWorkAddress()));
		dto.setUsername(entity.getUsername());
		dto.setProvider(entity.getProvider());
		dto.setCarpool(carpoolMapper.toDto(entity.getCarpool()) );
		List<RatingEntity> ratings = new ArrayList<>();
		dto.setRatings(ratings.stream().map(rating -> {
			return ratingMapper.toDto(rating);
		}).collect(Collectors.toList()));
		for (TakenRideEntity takenRide : entity.getTakenRides()) {
			dto.getTakenRides().add(new TakenRideDto(userMapper.toDto(entity),
					rideMapper.toDto(takenRide.getRide()), takenRide.isApproved(),takenRide.isDone()));
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
		entity.setHomeAddress(addressMapper.toEntity(dto.getHomeAddress()));
		entity.setWorkAddress(addressMapper.toEntity(dto.getWorkAddress()));
		entity.setUsername(dto.getUsername());
		entity.setProvider(dto.getProvider());
		entity.setCarpool(carpoolMapper.toEntity(dto.getCarpool()));
		List<RatingDto> ratings = new ArrayList<>();
		entity.setRatings(ratings.stream().map(rating -> {
			return ratingMapper.toEntity(rating);
		}).collect(Collectors.toList()));
		for (TakenRideDto takenRide : dto.getTakenRides()) {
			entity.getTakenRides().add(new TakenRideEntity(entity,
					rideMapper.toEntity(takenRide.getRide()), takenRide.isApproved(),takenRide.isDone()));
		}
		return entity;

	}
}
