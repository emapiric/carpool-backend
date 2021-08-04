package com.carpool.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carpool.dto.AuthenticationRequestDto;
import com.carpool.dto.UserDto;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
import com.carpool.mapper.UserEntityDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.repository.UserRepository;
import com.carpool.service.UserService;
import com.carpool.util.JwtUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserEntityDtoMapper userMapper;
	private RideRepository rideRepository;
	private JwtUtil jwtUtil;
	private PasswordEncoder passwordEncoder;
	private EmailSenderService emailSenderService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserEntityDtoMapper userMapper, RideRepository rideRepository,
			JwtUtil jwtUtil, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.rideRepository = rideRepository;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
		this.emailSenderService = emailSenderService;

	}

	@Override
	public void saveUser(UserDto user) throws Exception {
		List<UserEntity> existingUsers = userRepository.findByUsername(user.getUsername());
		if (existingUsers.size() == 0) {
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

	@Override
	public String register(@Valid UserDto user) throws Exception {
		Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent() && existingUser.get().getEnabled() != null
				&& existingUser.get().getEnabled() == true) {
			throw new Exception("Email is already taken");
		} else {
			String confirmationToken = jwtUtil.generateTokenFromString(user.getEmail());
			if (!existingUser.isPresent()) {
				user.setEnabled(false);
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				UserEntity userEntity = userMapper.toEntity(user);
				userEntity.setConfirmationToken(confirmationToken);
				userRepository.save(userEntity);
			} else {
				existingUser.get().setEnabled(false);
				existingUser.get().setConfirmationToken(confirmationToken);
				userRepository.save(existingUser.get());
			}

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("carpoolproject700@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/carpool-be/api/user/confirm-account?token=" + confirmationToken);

			emailSenderService.sendEmail(mailMessage);
		}
		return "Verification mail was sent";
	}

	@Override
	public String confirm(String confirmationToken) throws Exception {
		if (confirmationToken != null) {

			Optional<UserEntity> existingClient = userRepository.findByConfirmationToken(confirmationToken);
			if (!existingClient.isPresent()) {
				return "Invalid link";
			}
			if (jwtUtil.isTokenExpired(confirmationToken)) {
				return "Token has expired";
			}
			existingClient.get().setEnabled(true);
			existingClient.get().setConfirmationToken(null);
			userRepository.save(existingClient.get());

		} else {
			throw new Exception("Token is not present");
		}
		return "Successfully registered!";

	}

	@Override
	public String resetPassword(String token) {
		Optional<UserEntity> user = userRepository.findByResetPasswordToken(token).get(0);

		if (!user.isPresent()) {
			return "Invalid Token";
		}

		return "reset_password_form";
	}

	@Override
	public String processResetPassword(@Valid AuthenticationRequestDto request) {
		String token = request.getPasswordResetToken();
		String password = request.getPassword();
		Optional<UserEntity> user = userRepository.findByResetPasswordToken(token).get(0);
		if (!user.isPresent()) {
			return "Invalid Token";
		} else {
			if (token != null && jwtUtil.isTokenExpired(token)) {
				return "Token has expired";
			}

			user.get().setPassword(passwordEncoder.encode(password));
			user.get().setForgotPasswordToken(null);
			userRepository.save(user.get());
			return "You have successfully changed your password";
		}

	}

	@Override
	public String forgetPassword(@Valid AuthenticationRequestDto request) {
		Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
		String message;

		if (!user.isPresent()) {
			message = "Can not reset password";
		} else if (user.get().getEnabled() == true) {
			String email = request.getEmail();
			String token = jwtUtil.generateTokenFromString(email);

			try {
				updateResetPasswordToken(token, email);
				String resetPasswordLink = "http://localhost:8080/carpool-be/api/user" + "/reset_password?token="
						+ token;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.get().getEmail());
				mailMessage.setFrom("carpoolproject700@gmail.com");

				String text = "Hello, you have requested to change your password.\n"
						+ "Click the link below to reset it\n" + resetPasswordLink;

				mailMessage.setText(text);
				emailSenderService.sendEmail(mailMessage);

			} catch (Exception ex) {
				return ex.getMessage();
			}

			message = "Password reset email was sent";
		} else {

			message = "Can not reset password";
		}
		return message;
	}

	public void updateResetPasswordToken(String token, String email) {

		Optional<UserEntity> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			user.get().setForgotPasswordToken(token);
			userRepository.save(user.get());
		}
	}

}
