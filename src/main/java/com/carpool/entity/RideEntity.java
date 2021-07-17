package com.carpool.entity;

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
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TakenRideEntity> passengers = new ArrayList<>();
    private int capacity;
    @Column(name="date_time")
    private LocalDate dateTime = LocalDate.now();
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity from;
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity to;
    @Column(name="price_per_percon")
    private double pricePerPerson;
    @Column(name="is_carpool")
    private boolean isCarpool;
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity driver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideEntity)) return false;
        RideEntity that = (RideEntity) o;
        return capacity == that.capacity && Double.compare(that.pricePerPerson, pricePerPerson) == 0 && isCarpool == that.isCarpool && Objects.equals(id, that.id) && Objects.equals(passengers, that.passengers) && Objects.equals(dateTime, that.dateTime) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengers, capacity, dateTime, from, to, pricePerPerson, isCarpool, driver);
    }
}
