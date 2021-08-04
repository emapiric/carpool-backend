package com.carpool.dto;


import java.util.List;
import java.util.Objects;

import com.carpool.entity.UserEntity;

public class CarpoolDto implements MyDto{
    private static final long serialVersionUID = 1L;
    private Long id;
    private SimpleRideDto rideTo;
    private SimpleRideDto rideBack;
    private List<UserEntity> passengers;

    public CarpoolDto(Long id, SimpleRideDto rideTo, SimpleRideDto rideBack) {
        this.id = id;
        this.rideTo = rideTo;
        this.rideBack = rideBack;
    }

    public CarpoolDto() {
    }

    
    public List<UserEntity> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<UserEntity> passengers) {
		this.passengers = passengers;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleRideDto getRideTo() {
        return rideTo;
    }

    public void setRideTo(SimpleRideDto rideTo) {
        this.rideTo = rideTo;
    }

    public SimpleRideDto getRideBack() {
        return rideBack;
    }

    public void setRideBack(SimpleRideDto rideBack) {
        this.rideBack = rideBack;
    }

    @Override
    public String toString() {
        return "CarpoolDto{" +
                "id=" + id +
                ", rideTo=" + rideTo +
                ", rideBack=" + rideBack +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarpoolDto that = (CarpoolDto) o;
        return Objects.equals(id, that.id) && Objects.equals(rideTo, that.rideTo) && Objects.equals(rideBack, that.rideBack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rideTo, rideBack);
    }
}
