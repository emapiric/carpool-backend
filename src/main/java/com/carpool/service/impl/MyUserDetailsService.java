package com.carpool.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carpool.entity.UserEntity;
import com.carpool.mapper.UserEntityDtoMapper;
import com.carpool.repository.UserRepository;
import com.carpool.util.MyUser;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserEntityDtoMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		Optional<UserEntity> user = userRepository.findByEmail(s);

		if (!user.isPresent()) {
			return null;
		}

		if ((user.get()).getEnabled() == true) {
			return new MyUser(new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>()), 1);
		}

		return null;

	}
}
