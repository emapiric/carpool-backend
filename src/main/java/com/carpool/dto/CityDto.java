package com.carpool.dto;

import java.util.Objects;

public class CityDto implements MyDto{
    private static final long serialVersionUID = 1L;
    private Long postalCode;
    private String name;

    public CityDto(Long postalCode, String name) {
        this.postalCode = postalCode;
        this.name = name;
    }

    public CityDto() {
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
        return "CityDto{" +
                "postalCode=" + postalCode +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityDto)) return false;
        CityDto cityDto = (CityDto) o;
        return Objects.equals(postalCode, cityDto.postalCode) && Objects.equals(name, cityDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, name);
    }
}
