package com.carpool.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RideRequestDto implements MyDto{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime = LocalDateTime.now();
    private AddressRequestDto from;
    private AddressRequestDto to;
    private int numberOfPassangers;

    public RideRequestDto(LocalDateTime dateTime, AddressRequestDto from, AddressRequestDto to, int numberOfPassangers) {
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
        this.numberOfPassangers = numberOfPassangers;
    }

    public RideRequestDto(AddressRequestDto from, AddressRequestDto to) {
        this.from = from;
        this.to = to;
    }

    public RideRequestDto() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AddressRequestDto getFrom() {
        return from;
    }

    public void setFrom(AddressRequestDto from) {
        this.from = from;
    }

    public AddressRequestDto getTo() {
        return to;
    }

    public void setTo(AddressRequestDto to) {
        this.to = to;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    public void setNumberOfPassangers(int numberOfPassangers) {
        this.numberOfPassangers = numberOfPassangers;
    }

    @Override
    public String toString() {
        return "RideRequestDto{" +
                "dateTime=" + dateTime +
                ", from=" + from +
                ", to=" + to +
                ", numberOfPassangers=" + numberOfPassangers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideRequestDto that = (RideRequestDto) o;
        return numberOfPassangers == that.numberOfPassangers && Objects.equals(dateTime, that.dateTime) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, from, to, numberOfPassangers);
    }
}
