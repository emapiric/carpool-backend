package com.carpool.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.UserDto;
import com.carpool.entity.UserEntity;
import com.carpool.mapper.UserEntityDtoMapper;
import com.carpool.repository.UserRepository;
import com.carpool.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserEntityDtoMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserEntityDtoMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
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

}
