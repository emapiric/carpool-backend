package com.carpool.dto;

import java.util.Objects;

public class AddressRequestDto implements MyDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double latitude;
    private double longtitude;

    public AddressRequestDto(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public AddressRequestDto() {
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
        return "AddressRequestDto{" +
                "latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestDto that = (AddressRequestDto) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longtitude, longtitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longtitude);
    }
}
