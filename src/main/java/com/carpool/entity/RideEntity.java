package com.carpool.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalIdCache;


@Entity
@Table(name="ride")
@NaturalIdCache
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TakenRideEntity> passengers = new ArrayList<TakenRideEntity>();
    private int capacity;
    @Column(name="date_time")
    private LocalDateTime dateTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="address_from", referencedColumnName = "id")
    private AddressEntity from;
    @ManyToOne
    @JoinColumn(name="address_to", referencedColumnName = "id")
    private AddressEntity to;
    @Column(name="price_per_person")
    private double pricePerPerson;
    @OneToOne
    @JoinColumn(name="carpool_id", referencedColumnName = "id")
    private CarpoolEntity carpool;
    @ManyToOne
    @JoinColumn(name="driver",referencedColumnName = "id")
    private UserEntity driver;

    public RideEntity(Long id, List<TakenRideEntity> passengers, int capacity, LocalDateTime dateTime, AddressEntity from, AddressEntity to, double pricePerPerson, CarpoolEntity carpool, UserEntity driver) {
        this.id = id;
        this.passengers = passengers;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
        this.pricePerPerson = pricePerPerson;
        this.carpool = carpool;
        this.driver = driver;
    }

    public RideEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TakenRideEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<TakenRideEntity> passengers) {
        this.passengers = passengers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AddressEntity getFrom() {
        return from;
    }

    public void setFrom(AddressEntity from) {
        this.from = from;
    }

    public AddressEntity getTo() {
        return to;
    }

    public void setTo(AddressEntity to) {
        this.to = to;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public CarpoolEntity carpool() {
        return carpool;
    }

    public void setCarpool(CarpoolEntity carpool) {
        carpool = carpool;
    }

    public UserEntity getDriver() {
        return driver;
    }

    public void setDriver(UserEntity driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "RideEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", dateTime=" + dateTime +
                ", from=" + from +
                ", to=" + to +
                ", pricePerPerson=" + pricePerPerson +
                ", carpool=" + carpool +
                ", driver=" + driver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideEntity that = (RideEntity) o;
        return capacity == that.capacity && Double.compare(that.pricePerPerson, pricePerPerson) == 0 && Objects.equals(id, that.id) && Objects.equals(passengers, that.passengers) && Objects.equals(dateTime, that.dateTime) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(carpool, that.carpool) && Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengers, capacity, dateTime, from, to, pricePerPerson, carpool, driver);
    }

    public boolean hasSpace() {
        return this.passengers.size() < this.capacity;
    }
}
