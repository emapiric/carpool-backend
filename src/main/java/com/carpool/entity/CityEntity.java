package com.carpool.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="city")
public class CityEntity implements MyEntity{
    @Id
    @Column(name = "postal_code")
    private Long postalCode;
    private String name;

    public CityEntity(Long postalCode, String name) {
        this.postalCode = postalCode;
        this.name = name;
    }

    public CityEntity() {
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "postalCode=" + postalCode +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;
        CityEntity that = (CityEntity) o;
        return postalCode == that.postalCode && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, name);
    }
}
