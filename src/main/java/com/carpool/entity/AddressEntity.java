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
    private long id;
    private String street;
    private String number;
    @ManyToOne
    @JoinColumn(name="postal_code")
    private CityEntity city;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> usersLiving = new ArrayList<UserEntity>();
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> usersWorking = new ArrayList<UserEntity>();
    //da li cuvati listu voznji u kojima je bila ta adresa? treba i kao destinacija i kao polazna
    //tacka, malo bi bilo pretrpano


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city) && Objects.equals(usersLiving, that.usersLiving) && Objects.equals(usersWorking, that.usersWorking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city, usersLiving, usersWorking);
    }
}
