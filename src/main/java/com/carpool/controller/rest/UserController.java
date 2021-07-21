package com.carpool.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.UserDto;
import com.carpool.entity.UserEntity;
import com.carpool.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/registration")
	public ResponseEntity<Object> registerUserAccount(UserDto userDto) {
		System.out.println(userDto);
		try {
			userService.saveUser(userDto);
		} catch (Exception e) {

		}
		return ResponseEntity.status(HttpStatus.OK).body(userDto);

	}

	@PostMapping("/users/login")
	public ResponseEntity<Object> loginUser(@RequestBody UserDto user) {
		System.out.println(user);
		UserDto existingUser = userService.findUserByUsername(user.getUsername(), user.getPassword());
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.OK).body(existingUser);
		}
		return ResponseEntity.status(HttpStatus.OK).body("User not found");

	}

}
