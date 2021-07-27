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
	private CarpoolDto carpool;
	private SimpleUserDto driver;

	public SimpleRideDto(Long id, int capacity, LocalDateTime dateTime, AddressDto from, AddressDto to,
			double pricePerPerson, CarpoolDto carpool, SimpleUserDto driver) {
		this.id = id;
		this.capacity = capacity;
		this.dateTime = dateTime;
		this.from = from;
		this.to = to;
		this.pricePerPerson = pricePerPerson;
		this.carpool = carpool;
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

	public CarpoolDto carpool() {
		return carpool;
	}

	public void setCarpool(CarpoolDto carpool) {
		carpool = carpool;
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
				+ ", to=" + to + ", pricePerPerson=" + pricePerPerson + ", carpool=" + carpool + ", driver="
				+ driver + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SimpleRideDto that = (SimpleRideDto) o;
		return capacity == that.capacity && Double.compare(that.pricePerPerson, pricePerPerson) == 0 && Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(carpool, that.carpool) && Objects.equals(driver, that.driver);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, capacity, dateTime, from, to, pricePerPerson, carpool, driver);
	}

}
