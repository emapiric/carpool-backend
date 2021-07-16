package com.carpool.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Long id;
    @Column(name="number_of_passangers")
    private int numberOfPassangers;
    private int capacity;
    @Column(name="date_time")
    private LocalDate dateTime = LocalDate.now();
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity from;
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity to;
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity driver;
    @Column(name="is_carpool")
    private boolean isCarpool;
    @Column(name="is_done")
    private boolean isDone;
    @Column(name="price_per_percon")
    private BigDecimal pricePerPerson;
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TakenRideEntity> passengers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideEntity)) return false;
        RideEntity that = (RideEntity) o;
        return id == that.id && numberOfPassangers == that.numberOfPassangers && capacity == that.capacity && isCarpool == that.isCarpool && isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfPassangers, capacity, dateTime, from, to, driver, isCarpool, isDone, pricePerPerson);
    }
}
