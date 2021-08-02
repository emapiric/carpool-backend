package com.carpool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.UserDto;
import com.carpool.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/registration")
	public ResponseEntity<Object> registerUserAccount(@RequestBody UserDto userDto) {
		try {
			userService.saveUser(userDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(userDto);

	}

	@PostMapping("login")
	public ResponseEntity<Object> loginUser(@RequestBody UserDto user) {
		UserDto existingUser = userService.findUserByUsername(user.getUsername(), user.getPassword());
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.OK).body(existingUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");

	}

	@PostMapping("/addRide/{userId}/{rideId}")
	public @ResponseBody ResponseEntity<Object> addRide(@RequestParam Long userId, @RequestParam Long rideId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.addRide(userId, rideId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("/removeRide/{userId}/{rideId}")
	public @ResponseBody ResponseEntity<Object> removeRide(@RequestParam Long userId, @RequestParam Long rideId) {
		try {
			System.out.println("controller");
			return ResponseEntity.status(HttpStatus.OK).body(userService.removeRide(userId, rideId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
