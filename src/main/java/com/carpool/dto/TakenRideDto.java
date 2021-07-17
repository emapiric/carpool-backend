package com.carpool.dto;

import com.carpool.entity.TakenRideId;

import java.util.Objects;

public class TakenRideDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private TakenRideId id;
    private UserDto user;
    private RideDto ride;
    private boolean isApproved;
    private boolean isDone;

    public TakenRideDto(TakenRideId id, UserDto user, RideDto ride, boolean isApproved, boolean isDone) {
        this.id = id;
        this.user = user;
        this.ride = ride;
        this.isApproved = isApproved;
        this.isDone = isDone;
    }

    public TakenRideDto() {
    }

    public TakenRideId getId() {
        return id;
    }

    public void setId(TakenRideId id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public RideDto getRide() {
        return ride;
    }

    public void setRide(RideDto ride) {
        this.ride = ride;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "TakenRideDto{" +
                "id=" + id +
                ", user=" + user +
                ", ride=" + ride +
                ", isApproved=" + isApproved +
                ", isDone=" + isDone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TakenRideDto)) return false;
        TakenRideDto that = (TakenRideDto) o;
        return isApproved == that.isApproved && isDone == that.isDone && Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(ride, that.ride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, ride, isApproved, isDone);
    }
}
