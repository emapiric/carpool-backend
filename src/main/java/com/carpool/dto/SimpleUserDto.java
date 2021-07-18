package com.carpool.dto;

import java.util.Objects;

public class SimpleUserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private AddressDto workAddress;
    private AddressDto homeAddress;
    private CarDto car;

    public SimpleUserDto(Long id, String username, String email, String password, String fullName, String phone, AddressDto workAddress, AddressDto homeAddress, CarDto car) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.workAddress = workAddress;
        this.homeAddress = homeAddress;
        this.car = car;
    }

    public SimpleUserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDto getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressDto workAddress) {
        this.workAddress = workAddress;
    }

    public AddressDto getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressDto homeAddress) {
        this.homeAddress = homeAddress;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "SimpleUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", workAddress=" + workAddress +
                ", homeAddress=" + homeAddress +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleUserDto)) return false;
        SimpleUserDto that = (SimpleUserDto) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(fullName, that.fullName) && Objects.equals(phone, that.phone) && Objects.equals(workAddress, that.workAddress) && Objects.equals(homeAddress, that.homeAddress) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, fullName, phone, workAddress, homeAddress, car);
    }
}
