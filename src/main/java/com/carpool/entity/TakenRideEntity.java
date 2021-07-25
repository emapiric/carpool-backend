package com.carpool.entity;

import javax.persistence.*;

import java.util.Objects;

@Entity(name="TakenRide")
@Table(name="taken_ride")
public class TakenRideEntity {
	
	@EmbeddedId
	private TakenRideId id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private UserEntity user;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("rideId")
	private RideEntity ride;
	@Column(name="is_approved")
	private boolean isApproved;
	@Column(name="is_done")
	private boolean isDone;

	public TakenRideEntity(UserEntity user, RideEntity ride, boolean isApproved, boolean isDone) {
		this.id = new TakenRideId(user.getId(),ride.getId());
		this.user = user;
		this.ride = ride;
		this.isApproved = isApproved;
		this.isDone = isDone;
	}
	
	public TakenRideEntity(UserEntity user, RideEntity ride) {
		this.id = new TakenRideId(user.getId(),ride.getId());
		this.user = user;
		this.ride = ride;
	}

	public TakenRideEntity() {
	}

	public TakenRideId getId() {
		return id;
	}

	public void setId(TakenRideId id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RideEntity getRide() {
		return ride;
	}

	public void setRide(RideEntity ride) {
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
		return "TakenRideEntity{" +
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

		if (o == null || getClass() != o.getClass())
			return false;

		TakenRideEntity that = (TakenRideEntity) o;
		return Objects.equals(ride, that.ride) &&
				Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, ride);
	}
	

}
