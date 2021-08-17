package com.carpool.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto implements MyDto {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull
    @Size(min = 3)
    private String username;
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$")
    private String password;
    @Size(min = 3)
    private String fullName;
    private String phone;
    private CarDto car;
    private List<RatingDto> ratings = new ArrayList<RatingDto>();
    private List<TakenRideDto> takenRides = new ArrayList<TakenRideDto>();
    private List<RideDto> drivenRides = new ArrayList<RideDto>();
    private Boolean enabled;

    public UserDto(Long id, String username, String email, String password, String fullName, String phone, CarDto car,
                   List<RatingDto> ratings, List<TakenRideDto> takenRides, List<RideDto> drivenRides, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.car = car;
        this.ratings = ratings;
        this.takenRides = takenRides;
        this.drivenRides = drivenRides;
        this.enabled = enabled;
    }

    public UserDto() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<RatingDto> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDto> ratings) {
        this.ratings = ratings;
    }

    public List<TakenRideDto> getTakenRides() {
        return takenRides;
    }

    public void setTakenRides(List<TakenRideDto> takenRides) {
        this.takenRides = takenRides;
    }

    public List<RideDto> getDrivenRides() {
        return drivenRides;
    }

    public void setDrivenRides(List<RideDto> drivenRides) {
        this.drivenRides = drivenRides;
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", password='"
                + password + '\'' + ", fullName='" + fullName + '\'' + ", phone='" + phone + '\'' + ", car=" + car
                + ", ratings=" + ratings + ", takenRides=" + takenRides + ", drivenRides=" + drivenRides + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserDto))
            return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username)
                && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password)
                && Objects.equals(fullName, userDto.fullName) && Objects.equals(phone, userDto.phone)
                && Objects.equals(ratings, userDto.ratings) && Objects.equals(takenRides, userDto.takenRides)
                && Objects.equals(drivenRides, userDto.drivenRides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, fullName, phone, car,
                ratings, takenRides, drivenRides);
    }
}
