package com.carpool.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="ride")
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberOfPassangers;
    private int capacity;
    private LocalDate dateTime = LocalDate.now();
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity from;
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity to;
    @ManyToOne
    @JoinColumn(name="id")
    private CarEntity car;
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity driver;
    private boolean isCarpool;
    private boolean isDone;

    //jel sigurno decimal stavljamo?
    private BigDecimal pricePerPerson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideEntity)) return false;
        RideEntity that = (RideEntity) o;
        return id == that.id && numberOfPassangers == that.numberOfPassangers && capacity == that.capacity && isCarpool == that.isCarpool && isDone == that.isDone && Objects.equals(dateTime, that.dateTime) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(car, that.car) && Objects.equals(driver, that.driver) && Objects.equals(pricePerPerson, that.pricePerPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfPassangers, capacity, dateTime, from, to, car, driver, isCarpool, isDone, pricePerPerson);
    }
}
