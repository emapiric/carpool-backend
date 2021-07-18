package com.carpool.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class CarDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private String id;
    @NotNull
    private String manufacturer;
    @NotNull
    private String model;
    @NotNull
    private String type;
    @NotNull
    private LocalDate yearOfManufacturing = LocalDate.now();
    @NotNull
    private String color;
    @NotNull
    private UserDto owner;

    public CarDto(String id, String manufacturer, String model, String type, LocalDate yearOfManufacturing, String color, UserDto owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.yearOfManufacturing = yearOfManufacturing;
        this.color = color;
        this.owner = owner;
    }

    public CarDto() {
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

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", yearOfManufacturing=" + yearOfManufacturing +
                ", color='" + color + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDto)) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(id, carDto.id) && Objects.equals(manufacturer, carDto.manufacturer) && Objects.equals(model, carDto.model) && Objects.equals(type, carDto.type) && Objects.equals(yearOfManufacturing, carDto.yearOfManufacturing) && Objects.equals(color, carDto.color) && Objects.equals(owner, carDto.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, type, yearOfManufacturing, color, owner);
    }
}
