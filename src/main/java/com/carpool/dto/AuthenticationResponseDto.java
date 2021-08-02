package com.carpool.dto;

import java.io.Serializable;
import java.util.Objects;

public class AuthenticationResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String jwt;
	private final int type;

	public AuthenticationResponseDto(String jwt, int type) {
		this.jwt = jwt;
		this.type = type;
	}

	public String getJwt() {
		return jwt;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return "AuthenticationResponseDTO [jwt=" + jwt + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(jwt, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponseDto other = (AuthenticationResponseDto) obj;
		return Objects.equals(jwt, other.jwt) && type == other.type;
	}

}
