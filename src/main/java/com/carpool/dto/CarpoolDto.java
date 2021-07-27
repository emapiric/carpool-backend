package com.carpool.dto;


import java.util.Objects;

public class CarpoolDto implements MyDto{
    private static final long serialVersionUID = 1L;
    private Long id;
    private SimpleRideDto rideTo;
    private SimpleRideDto rideBack;

    public CarpoolDto(Long id, SimpleRideDto rideTo, SimpleRideDto rideBack) {
        this.id = id;
        this.rideTo = rideTo;
        this.rideBack = rideBack;
    }

    public CarpoolDto() {
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
