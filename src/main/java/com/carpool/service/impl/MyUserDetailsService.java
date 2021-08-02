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
import com.carpool.service.UserService;
import com.carpool.util.MyUser;
import com.carpool.util.Provider;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserEntityDtoMapper userMapper;;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		Optional<UserEntity> user = userRepository.findByEmail(s);

		if (user.isEmpty()) {
			return null;
		}

		
			if ((user.get()).getEnabled() == true) {
				return new MyUser(new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>()), 1);
			} else if ( user.get().getProvider() == Provider.LOCAL) {
				try {
					userService.register(userMapper.toDto(user.get()));
				} catch (Exception e) {
					return null;
				}
			
		} else {
			return new MyUser(new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>()), 2);
		}

		return null;

	}
}
