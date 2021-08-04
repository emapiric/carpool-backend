package com.carpool.service;

import javax.validation.Valid;

import com.carpool.dto.AuthenticationRequestDto;
import com.carpool.dto.UserDto;

public interface UserService {

	public void saveUser(UserDto user) throws Exception;

	public UserDto removeRide(Long rideId, Long userId) throws Exception;

	public UserDto addRide(Long rideId, Long rideId2) throws Exception;

	public String register(@Valid UserDto user) throws Exception;

	public String confirm(String confirmationToken) throws Exception;

	public String resetPassword(String token);

	public String processResetPassword(@Valid AuthenticationRequestDto request);

	public String forgetPassword(@Valid AuthenticationRequestDto request);

}
