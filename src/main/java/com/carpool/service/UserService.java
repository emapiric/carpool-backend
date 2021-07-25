package com.carpool.service;

import com.carpool.dto.UserDto;

public interface UserService {
	
	public UserDto findUserByUsername(String username, String password);
	public void saveUser(UserDto user) throws Exception;
	public UserDto removeRide(Long rideId, Long userId) throws Exception;
	public UserDto addRide(Long rideId, Long rideId2) throws Exception;
	
}
