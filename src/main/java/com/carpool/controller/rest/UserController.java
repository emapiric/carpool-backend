package com.carpool.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.AuthenticationRequestDto;
import com.carpool.dto.AuthenticationResponseDto;
import com.carpool.dto.UserDto;
import com.carpool.service.UserService;
import com.carpool.service.impl.MyUserDetailsService;
import com.carpool.util.JwtUtil;
import com.carpool.util.MyUser;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private MyUserDetailsService userDetailsService;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			MyUserDetailsService userDetailsService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

//	@PostMapping("/registration")
//	public ResponseEntity<Object> registerUserAccount(@RequestBody UserDto userDto) {
//		try {
//			userService.saveUser(userDto);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(userDto);
//
//	}
//
//	@PostMapping("/users/login")
//	public ResponseEntity<Object> loginUser(@RequestBody UserDto user) {
//		UserDto existingUser = userService.findUserByUsername(user.getUsername(), user.getPassword());
//		if (existingUser != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(existingUser);
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
//
//	}

	@PostMapping(value = "/registration")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid UserDto user, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = new HashMap<>();
				bindingResult.getAllErrors().forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering client " + errors);

			} else {
				return ResponseEntity.status(HttpStatus.OK).body(userService.register(user));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET })
	public ResponseEntity<Object> confirmUserAccountTest(@RequestParam("token") String confirmationToken) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.confirm(confirmationToken));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.POST })
	public ResponseEntity<Object> confirmUserAccount(@RequestBody String confirmationToken) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.confirm(confirmationToken));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/forgot_password")
	public ResponseEntity<Object> processForgotPassword(@RequestBody @Valid AuthenticationRequestDto request,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = new HashMap<>();
				bindingResult.getAllErrors().forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering client " + errors);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(userService.forgetPassword(request));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@GetMapping("/reset_password")
	public ResponseEntity<Object> showResetPasswordForm(@Param(value = "token") String token) {
		try {

			return ResponseEntity.status(HttpStatus.OK).body(userService.resetPassword(token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PostMapping("/reset_password")
	public ResponseEntity<Object> processResetPassword(@RequestBody @Valid AuthenticationRequestDto request,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = new HashMap<>();
				bindingResult.getAllErrors().forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering client " + errors);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(userService.processResetPassword(request));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PostMapping("/addRide")
	public @ResponseBody ResponseEntity<Object> addRide(@RequestParam Long userId, @RequestParam Long rideId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.addRide(userId, rideId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("/removeRide")
	public @ResponseBody ResponseEntity<Object> removeRide(@RequestParam Long userId, @RequestParam Long rideId) {
		try {
			System.out.println("controller");
			return ResponseEntity.status(HttpStatus.OK).body(userService.removeRide(userId, rideId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody @Valid AuthenticationRequestDto authenticationRequest, BindingResult bindingResult)
			throws Exception {

		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = new HashMap<>();
				bindingResult.getAllErrors().forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering client " + errors);

			} else {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(), authenticationRequest.getPassword()));
			}
		} catch (Exception e) {
			// throw new Exception("Incorrect username or password", e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect username or password");

		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponseDto(jwt, ((MyUser) userDetails).getType()));
	}

}
