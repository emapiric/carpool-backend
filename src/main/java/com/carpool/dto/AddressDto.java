package com.carpool.dto;

import java.util.Objects;

public class AddressDto implements MyDto {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String street;
    private String number;
    private CityDto city;
    private double latitude;
    private double longtitude;

    public AddressDto(Long id, String street, String number, CityDto city, double latitude, double longtitude) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public AddressDto() {
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

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
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
        return "AddressDto{" +
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
        if (!(o instanceof AddressDto)) return false;
        AddressDto that = (AddressDto) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longtitude, longtitude) == 0 && Objects.equals(id, that.id) && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city, latitude, longtitude);
    }
}
