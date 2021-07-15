package com.carpool.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="taken_ride")
public class TakenRideEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name="id")
    private RideEntity ride;
    private boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TakenRideEntity)) return false;
        TakenRideEntity that = (TakenRideEntity) o;
        return id == that.id && isApproved == that.isApproved && Objects.equals(user, that.user) && Objects.equals(ride, that.ride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, ride, isApproved);
    }
}
