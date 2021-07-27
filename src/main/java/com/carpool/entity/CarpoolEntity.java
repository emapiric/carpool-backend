package com.carpool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="carpool")
public class CarpoolEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    private RideEntity rideTo;
    @OneToOne
    @JoinColumn(name = "id")
    private RideEntity rideBack;

    public CarpoolEntity(Long id, RideEntity rideTo, RideEntity rideBack) {
        this.id = id;
        this.rideTo = rideTo;
        this.rideBack = rideBack;
    }

    public CarpoolEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RideEntity getRideTo() {
        return rideTo;
    }

    public void setRideTo(RideEntity rideTo) {
        this.rideTo = rideTo;
    }

    public RideEntity getRideBack() {
        return rideBack;
    }

    public void setRideBack(RideEntity rideBack) {
        this.rideBack = rideBack;
    }

    @Override
    public String toString() {
        return "CarpoolEntity{" +
                "id=" + id +
                ", rideTo=" + rideTo +
                ", rideBack=" + rideBack +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarpoolEntity that = (CarpoolEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(rideTo, that.rideTo) && Objects.equals(rideBack, that.rideBack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rideTo, rideBack);
    }
}
