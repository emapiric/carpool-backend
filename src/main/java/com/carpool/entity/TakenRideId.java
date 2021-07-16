package com.carpool.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class TakenRideId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private long userId;
	@Column
	private long rideId;

	public TakenRideId(Long userId, Long rideId) {
		this.userId = userId;
		this.rideId = rideId;
	}

	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        TakenRideId that = (TakenRideId) o;
	        return Objects.equals(userId, that.userId) &&
	               Objects.equals(rideId, that.rideId);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(userId, rideId);
	    }
	
	
}
