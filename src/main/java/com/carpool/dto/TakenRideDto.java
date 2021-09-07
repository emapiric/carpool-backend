package com.carpool.dto;

import com.carpool.entity.TakenRideId;

import java.util.Objects;

public class TakenRideDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private TakenRideId id;
    private SimpleUserDto user;
    private SimpleRideDto ride;
    private boolean isApproved;

    public TakenRideDto(SimpleUserDto user, SimpleRideDto ride, boolean isApproved) {
        this.id = new TakenRideId(user.getId(),ride.getId());
        this.user = user;
        this.ride = ride;
        this.isApproved = isApproved;
    }

    public TakenRideDto() {
    }

    public TakenRideId getId() {
        return id;
    }

    public void setId(TakenRideId id) {
        this.id = id;
    }

    public SimpleUserDto getUser() {
        return user;
    }

    public void setUser(SimpleUserDto user) {
        this.user = user;
    }

    public SimpleRideDto getRide() {
        return ride;
    }

    public void setRide(SimpleRideDto ride) {
        this.ride = ride;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    @Override
    public String toString() {
        return "TakenRideDto{" +
                "id=" + id +
                ", user=" + user +
                ", ride=" + ride +
                ", isApproved=" + isApproved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TakenRideDto)) return false;
        TakenRideDto that = (TakenRideDto) o;
        return isApproved == that.isApproved &&  Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(ride, that.ride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, ride, isApproved);
    }
}
