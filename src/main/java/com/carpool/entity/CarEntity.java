package com.carpool.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class CarEntity implements MyEntity {
	@Id
	@Column(name = "plate_number")
	private String id;
	private String manufacturer;
	private String model;
	private String type;
	@Column(name = "year_of_manufacturing")
	private LocalDate yearOfManufacturing = LocalDate.now();
	private String color;

	public CarEntity(String id, String manufacturer, String model, String type, LocalDate yearOfManufacturing,
			String color) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.type = type;
		this.yearOfManufacturing = yearOfManufacturing;
		this.color = color;
	}

	public CarEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getYearOfManufacturing() {
		return yearOfManufacturing;
	}

	public void setYearOfManufacturing(LocalDate yearOfManufacturing) {
		this.yearOfManufacturing = yearOfManufacturing;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CarEntity{" + "id=" + id + ", manufacturer='" + manufacturer + '\'' + ", model='" + model + '\''
				+ ", type='" + type + '\'' + ", yearOfManufacturing=" + yearOfManufacturing + ", color='" + color + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CarEntity))
			return false;
		CarEntity carEntity = (CarEntity) o;
		return id == carEntity.id && Objects.equals(manufacturer, carEntity.manufacturer)
				&& Objects.equals(model, carEntity.model) && Objects.equals(type, carEntity.type)
				&& Objects.equals(yearOfManufacturing, carEntity.yearOfManufacturing)
				&& Objects.equals(color, carEntity.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, manufacturer, model, type, yearOfManufacturing, color);
	}
}
