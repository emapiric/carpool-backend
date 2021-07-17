package com.carpool.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RideDto implements MyDto{
    private static final long serialVersionUID = 1L;
    private Long id;
    private List<TakenRideDto> passengers = new ArrayList<TakenRideDto>();
    @NotNull
    @Positive
    private int capacity;
    private LocalDate dateTime = LocalDate.now();
    @NotNull
    private AddressDto from;
    @NotNull
    private AddressDto to;
    @NotNull
    @Positive
    private double pricePerPerson;
    private boolean isCarpool;
    @NotNull
    private UserDto driver;

    public RideDto(Long id, List<TakenRideDto> passengers, int capacity, LocalDate dateTime, AddressDto from, AddressDto to, double pricePerPerson, boolean isCarpool, UserDto driver) {
        this.id = id;
        this.passengers = passengers;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
        this.pricePerPerson = pricePerPerson;
        this.isCarpool = isCarpool;
        this.driver = driver;
    }

    public RideDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TakenRideDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<TakenRideDto> passengers) {
        this.passengers = passengers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public AddressDto getFrom() {
        return from;
    }

    public void setFrom(AddressDto from) {
        this.from = from;
    }

    public AddressDto getTo() {
        return to;
    }

    public void setTo(AddressDto to) {
        this.to = to;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public boolean isCarpool() {
        return isCarpool;
    }

    public void setCarpool(boolean carpool) {
        isCarpool = carpool;
    }

    public UserDto getDriver() {
        return driver;
    }

    public void setDriver(UserDto driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "RideDto{" +
                "id=" + id +
                ", passengers=" + passengers +
                ", capacity=" + capacity +
                ", dateTime=" + dateTime +
                ", from=" + from +
                ", to=" + to +
                ", pricePerPerson=" + pricePerPerson +
                ", isCarpool=" + isCarpool +
                ", driver=" + driver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideDto)) return false;
        RideDto rideDto = (RideDto) o;
        return capacity == rideDto.capacity && Double.compare(rideDto.pricePerPerson, pricePerPerson) == 0 && isCarpool == rideDto.isCarpool && Objects.equals(id, rideDto.id) && Objects.equals(passengers, rideDto.passengers) && Objects.equals(dateTime, rideDto.dateTime) && Objects.equals(from, rideDto.from) && Objects.equals(to, rideDto.to) && Objects.equals(driver, rideDto.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengers, capacity, dateTime, from, to, pricePerPerson, isCarpool, driver);
    }
}
