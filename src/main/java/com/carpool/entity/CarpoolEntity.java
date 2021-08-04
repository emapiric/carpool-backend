package com.carpool.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="carpool")
public class CarpoolEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="ride_to_id", referencedColumnName = "id")
    private RideEntity rideTo;
    @OneToOne
    @JoinColumn(name="ride_back_id", referencedColumnName = "id")
    private RideEntity rideBack;
    @OneToMany(mappedBy="carpool", cascade = CascadeType.ALL)
    private List<UserEntity> passengers;
    public CarpoolEntity(Long id, RideEntity rideTo, RideEntity rideBack) {
        this.id = id;
        this.rideTo = rideTo;
        this.rideBack = rideBack;
    }
    
    

    public CarpoolEntity(Long id, RideEntity rideTo, RideEntity rideBack, List<UserEntity> passengers) {
		super();
		this.id = id;
		this.rideTo = rideTo;
		this.rideBack = rideBack;
		this.passengers = passengers;
	}



	public List<UserEntity> getPassengers() {
		return passengers;
	}


	public void setPassengers(List<UserEntity> passengers) {
		this.passengers = passengers;
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
