package com.carpool.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="car")
public class CarEntity implements MyEntity {
    @Id
    @Column(name = "plate_number")
    private Long id;
    private String manufacturer;
    private String model;
    private String type;
    @Column(name = "year_of_manufacturing")
    private LocalDate yearOfManufacturing = LocalDate.now();
    private String color;
    @OneToOne
    private UserEntity owner;

    public CarEntity(Long id, String manufacturer, String model, String type, LocalDate yearOfManufacturing, String color, UserEntity owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.yearOfManufacturing = yearOfManufacturing;
        this.color = color;
        this.owner = owner;
    }

    public CarEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
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
        if (!(o instanceof CarEntity)) return false;
        CarEntity carEntity = (CarEntity) o;
        return id == carEntity.id && Objects.equals(manufacturer, carEntity.manufacturer) && Objects.equals(model, carEntity.model) && Objects.equals(type, carEntity.type) && Objects.equals(yearOfManufacturing, carEntity.yearOfManufacturing) && Objects.equals(color, carEntity.color) && Objects.equals(owner, carEntity.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, type, yearOfManufacturing, color, owner);
    }
}
