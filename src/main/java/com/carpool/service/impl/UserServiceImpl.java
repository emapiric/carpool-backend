package com.carpool.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.carpool.service.NotificationService;
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
    private NotificationService notificationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserEntityDtoMapper userMapper, RideRepository rideRepository,
                           JwtUtil jwtUtil, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.rideRepository = rideRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
        this.notificationService = notificationService;

    }

    @Override
    public void saveUser(UserDto user) throws Exception {
        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.isPresent()) {
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
        TakenRideEntity takenRide = new TakenRideEntity(user, ride, false, false);
        user.addTakenRide(takenRide);

        //notificationService.notifyDriver(takenRide);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public String register(@Valid UserDto user) throws Exception {
        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getEnabled() != null && existingUser.get().getEnabled() == true) {
            throw new Exception("Email is already taken");
        }
        String confirmationToken = jwtUtil.generateTokenFromString(user.getEmail());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("carpoolproject700@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8100/?token=" + confirmationToken);
        try {
            emailSenderService.sendEmail(mailMessage);
        } catch (Exception e) {
            throw new Exception("Invalid mail");
        }
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
        return "Verification mail was sent";
    }

    @Override
    public String confirm(String confirmationToken) throws Exception {
        if (confirmationToken != null) {
            try {
                if (jwtUtil.isTokenExpired(confirmationToken)) {
                    throw new Exception("Token has expired");
                }
            } catch (Exception e) {
                throw new Exception("Token has expired");
            }
            Optional<UserEntity> existingClient = userRepository.findByConfirmationToken(confirmationToken);
            if (!existingClient.isPresent()) {
                throw new Exception("Invalid link");
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
    public String resetPassword(String token) throws Exception {
        Optional<UserEntity> user = userRepository.findByResetPasswordToken(token).get(0);
        if (!user.isPresent()) {
            throw new Exception("Invalid Token");
        }
        return "reset_password_form";
    }

    @Override
    public String processResetPassword(@Valid AuthenticationRequestDto request) throws Exception {
        String token = request.getPasswordResetToken();
        String password = request.getPassword();
        Optional<UserEntity> user = userRepository.findByResetPasswordToken(token).get(0);
        if (!user.isPresent()) {
            return "Invalid Token";
        }
        if (token != null && jwtUtil.isTokenExpired(token)) {
            throw new Exception("Token has expired");
        }
        user.get().setPassword(passwordEncoder.encode(password));
        user.get().setForgotPasswordToken(null);
        userRepository.save(user.get());
        return "You have successfully changed your password";
    }

    @Override
    public String forgetPassword(@Valid AuthenticationRequestDto request) throws Exception {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        if (!user.isPresent() || !user.get().getEnabled()) {
            throw new Exception("Can not reset password");
        }
        String email = request.getEmail();
        String token = jwtUtil.generateTokenFromString(email);
        try {
            updateResetPasswordToken(token, email);
            String resetPasswordLink = "http://localhost:8080/carpool-be/api/user" + "/reset_password?token=" + token;
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.get().getEmail());
            mailMessage.setFrom("carpoolproject700@gmail.com");
            String text = "Hello, you have requested to change your password.\n"
                    + "Click the link below to reset it\n" + resetPasswordLink;
            mailMessage.setText(text);
            emailSenderService.sendEmail(mailMessage);
        } catch (Exception ex) {
            throw new Exception("Invalid mail");
        }
        return "Password reset email was sent";
    }

    @Override
    public UserDto findByUsername(String username) throws Exception {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new Exception("User doesn't exist");
        }
        return userMapper.toDto(user.get());
    }

    @Override
    public UserDto findByEmail(String email) throws Exception {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new Exception("User doesn't exist");
        }
        return userMapper.toDto(user.get());
    }

    public void updateResetPasswordToken(String token, String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().setForgotPasswordToken(token);
            userRepository.save(user.get());
        }
    }

    @Override
    public void addTokenToUser(String email, String jwt) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().setJwt(jwt);
            userRepository.save(user.get());
        }
    }

}
