package com.carpool.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.MyLoggerClass;
import com.carpool.dto.UserDto;
import com.carpool.entity.UserEntity;
import com.carpool.mapper.UserEntityDtoMapper;
import com.carpool.repository.UserRepository;
import com.carpool.util.Provider;

@Service
public class GoogleLoginService {

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private UserEntityDtoMapper userMapper;

	public synchronized UserDto processOAuthPostLogin(String email, String username)
			throws org.springframework.dao.DataIntegrityViolationException {

		synchronized (GoogleLoginService.class) {

			Optional<UserEntity> existUser = userRepository.findByEmail(email);

			if (existUser.isEmpty()) {

				UserDto ud = new UserDto();
				ud.setEmail(email);
				ud.setProvider(Provider.GOOGLE);
				ud.setEnabled(true);
				ud.setUsername(username);

				try {
					UserEntity user = userRepository.save(userMapper.toEntity(ud));
					return userMapper.toDto(user);
				} catch (org.springframework.dao.DataIntegrityViolationException e) {
					MyLoggerClass.log.info("User " + email + " is already logged in but thread tried to save it");
					return null;

				}

			} else {
				UserEntity user;
				
				user = existUser.get();
					if(user.getEnabled()==false) {
						user.setProvider(Provider.GOOGLE);
						user.setEnabled(true);
						user.setConfirmationToken(null);
						userRepository.save(user);
					}
					return userMapper.toDto(user);
				

			}

		}

	}

}

