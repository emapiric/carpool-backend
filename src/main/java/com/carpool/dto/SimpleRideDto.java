package com.carpool.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SimpleRideDto implements MyDto {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int capacity;
	private LocalDateTime dateTime = LocalDateTime.now();
	private AddressDto from;
	private AddressDto to;
	private double pricePerPerson;
	private boolean isCarpool;
	private SimpleUserDto driver;

	public SimpleRideDto(Long id, int capacity, LocalDateTime dateTime, AddressDto from, AddressDto to,
			double pricePerPerson, boolean isCarpool, SimpleUserDto driver) {
		this.id = id;
		this.capacity = capacity;
		this.dateTime = dateTime;
		this.from = from;
		this.to = to;
		this.pricePerPerson = pricePerPerson;
		this.isCarpool = isCarpool;
		this.driver = driver;
	}

	public SimpleRideDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SimpleUserDto getDriver() {
		return driver;
	}

	public void setDriver(SimpleUserDto driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "RideDto{" + "id=" + id + ", capacity=" + capacity + ", dateTime=" + dateTime + ", from=" + from
				+ ", to=" + to + ", pricePerPerson=" + pricePerPerson + ", isCarpool=" + isCarpool + ", driver="
				+ driver + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RideDto))
			return false;
		SimpleRideDto rideDto = (SimpleRideDto) o;
		return capacity == rideDto.capacity && Double.compare(rideDto.pricePerPerson, pricePerPerson) == 0
				&& isCarpool == rideDto.isCarpool && Objects.equals(id, rideDto.id)
				&& Objects.equals(dateTime, rideDto.dateTime) && Objects.equals(from, rideDto.from)
				&& Objects.equals(to, rideDto.to) && Objects.equals(driver, rideDto.driver);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, capacity, dateTime, from, to, pricePerPerson, isCarpool, driver);
	}

}
