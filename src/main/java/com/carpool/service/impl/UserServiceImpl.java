package com.carpool.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.UserDto;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
import com.carpool.mapper.UserEntityDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.repository.UserRepository;
import com.carpool.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserEntityDtoMapper userMapper;
	private RideRepository rideRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserEntityDtoMapper userMapper, RideRepository rideRepository) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.rideRepository = rideRepository;
	}

	@Override
	public UserDto findUserByUsername(String username, String password) {
		List<UserEntity> users = userRepository.findByUsername(username);
		if (users.size() != 0) {
			UserDto user = userMapper.toDto(users.get(0));
			if (user != null && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(UserDto user) throws Exception {
		List<UserEntity> existingUsers = userRepository.findByUsername(user.getUsername());
		if (existingUsers.size()==0) {
			userRepository.save(userMapper.toEntity(user));
		} else
			throw new Exception("User already exists");
	}

	@Override
	public UserDto removeRide(Long userId, Long rideId) throws Exception {
		Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
		if (!userEntityOptional.isPresent()) {
			throw new Exception("user does not exist");
		}
		Optional<RideEntity> rideEntityOptional = rideRepository.findById(rideId);
		if (!rideEntityOptional.isPresent()) {
			throw new Exception("ride does not exist");
		}
		RideEntity ride = rideEntityOptional.get();
		UserEntity user = userEntityOptional.get();
		user.removeTakenRide(ride);
		user = userRepository.save(user);
		return userMapper.toDto(user);
	}

	@Override
	public UserDto addRide(Long userId, Long rideId) throws Exception {
		Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
		if (!userEntityOptional.isPresent()) {
			throw new Exception("user does not exist");
		}
		Optional<RideEntity> rideEntityOptional = rideRepository.findById(rideId);
		if (!rideEntityOptional.isPresent()) {
			throw new Exception("ride does not exist");
		}

		UserEntity user = userEntityOptional.get();
		RideEntity ride = rideEntityOptional.get();
		user.addTakenRide(new TakenRideEntity(user, ride, false, false));
		userRepository.save(user);
		return userMapper.toDto(user);
	}



}
