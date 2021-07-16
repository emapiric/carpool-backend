package com.carpool.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
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
	
	public TakenRideEntity(UserEntity user, RideEntity ride, boolean isApproved) {
		this.id = new TakenRideId(user.getId(), ride.getId());
		this.user = user;
		this.ride = ride;
		this.isApproved = isApproved;
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
