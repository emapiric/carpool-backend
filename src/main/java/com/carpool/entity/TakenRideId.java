package com.carpool.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TakenRideId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private Long userId;
	@Column
	private Long rideId;

	public TakenRideId(Long userId, Long rideId) {
		this.userId = userId;
		this.rideId = rideId;
	}

	public TakenRideId() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRideId() {
		return rideId;
	}

	public void setRideId(long rideId) {
		this.rideId = rideId;
	}

	@Override
	public String toString() {
		return "TakenRideId{" +
				"userId=" + userId +
				", rideId=" + rideId +
				'}';
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
