package com.carpool.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class AuthenticationRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$")
	private String password;
	private String passwordResetToken;

	public AuthenticationRequestDto() {

	}

	public AuthenticationRequestDto(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public AuthenticationRequestDto(String email, String password, String passwordResetToken) {
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordResetToken(passwordResetToken);
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

	

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	@Override
	public String toString() {
		return "AuthenticationRequestDto [email=" + email + ", password=" + password + ", passwordResetToken="
				+ passwordResetToken +  "]";
	}
	
}
