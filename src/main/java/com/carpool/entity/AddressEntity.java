package com.carpool.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class AddressEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    @ManyToOne
    @JoinColumn(name="postal_code")
    private CityEntity city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city);
    }
}
