package com.carpool.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="address")
public class AddressEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    @ManyToOne
    @JoinColumn(name="postal_code")
    private CityEntity city;
    private double latitude;
    private double longtitude;

    public AddressEntity(Long id, String street, String number, CityEntity city, double latitude, double longtitude) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public AddressEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city=" + city +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;
        AddressEntity that = (AddressEntity) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longtitude, longtitude) == 0 && Objects.equals(id, that.id) && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city, latitude, longtitude);
    }
}
