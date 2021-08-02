package com.carpool.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AuthenticationRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
	private String email;
	private String password;
	private String passwordResetToken;
	@NotNull
	private String username;

	public AuthenticationRequestDto() {

	}

	public AuthenticationRequestDto(String email, String password, String username) {
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
	}

	public AuthenticationRequestDto(String email, String password, String username, String passwordResetToken) {
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordResetToken(passwordResetToken);
		this.setUsername(username);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	@Override
	public String toString() {
		return "AuthenticationRequestDto [email=" + email + ", password=" + password + ", passwordResetToken="
				+ passwordResetToken + ", username=" + username + "]";
	}
	
}
