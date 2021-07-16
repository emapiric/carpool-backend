package com.carpool.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
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
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<UserEntity>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarEntity)) return false;
        CarEntity carEntity = (CarEntity) o;
        return id == carEntity.id && Objects.equals(manufacturer, carEntity.manufacturer) && Objects.equals(model, carEntity.model) && Objects.equals(type, carEntity.type) && Objects.equals(yearOfManufacturing, carEntity.yearOfManufacturing) && Objects.equals(color, carEntity.color) && Objects.equals(users, carEntity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, type, yearOfManufacturing, color, users);
    }
}
