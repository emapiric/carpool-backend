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
@Table(name="city")
public class CityEntity {
    @Id
    @Column(name = "postal_code")
    private long postalCode;
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses = new ArrayList<AddressEntity>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;
        CityEntity that = (CityEntity) o;
        return postalCode == that.postalCode && Objects.equals(name, that.name) && Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, name, addresses);
    }
}
