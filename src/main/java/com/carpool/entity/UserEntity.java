package com.carpool.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class UserEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Column(name="full_name")
    private String fullName;
    private String phone;
    @ManyToOne
    @JoinColumn(name="workAddress", referencedColumnName = "id")
    private AddressEntity workAddress;
    @ManyToOne
    @JoinColumn(name="homeAddress", referencedColumnName = "id")
    private AddressEntity homeAddress;
    @OneToOne
    @JoinColumn(name="plate_number")
    private CarEntity car;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingTimeEntity> workDays = new ArrayList<WorkingTimeEntity>();
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingEntity> ratings = new ArrayList<RatingEntity>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TakenRideEntity> takenRides = new ArrayList<TakenRideEntity>();
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RideEntity> drivenRides = new ArrayList<RideEntity>();

    private void addTakenRide(RideEntity ride, boolean isApproved, boolean isDone){
        TakenRideEntity takenRide = new TakenRideEntity(this, ride, isApproved, isDone);
        takenRides.add(takenRide);
        ride.getPassengers().add(takenRide);
    }

    public UserEntity(Long id, String username, String email, String password, String fullName, String phone, AddressEntity workAddress, AddressEntity homeAddress, CarEntity car, List<WorkingTimeEntity> workDays, List<RatingEntity> ratings, List<TakenRideEntity> takenRides, List<RideEntity> drivenRides) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.workAddress = workAddress;
        this.homeAddress = homeAddress;
        this.car = car;
        this.workDays = workDays;
        this.ratings = ratings;
        this.takenRides = takenRides;
        this.drivenRides = drivenRides;
    }

    public UserEntity() {
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

    public AddressEntity getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressEntity workAddress) {
        this.workAddress = workAddress;
    }

    public AddressEntity getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressEntity homeAddress) {
        this.homeAddress = homeAddress;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public List<WorkingTimeEntity> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkingTimeEntity> workDays) {
        this.workDays = workDays;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public List<TakenRideEntity> getTakenRides() {
        return takenRides;
    }

    public void setTakenRides(List<TakenRideEntity> takenRides) {
        this.takenRides = takenRides;
    }

    public List<RideEntity> getDrivenRides() {
        return drivenRides;
    }

    public void setDrivenRides(List<RideEntity> drivenRides) {
        this.drivenRides = drivenRides;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", workAddress=" + workAddress +
                ", homeAddress=" + homeAddress +
                ", car=" + car +
                ", workDays=" + workDays +
                ", ratings=" + ratings +
                ", takenRides=" + takenRides +
                ", drivenRides=" + drivenRides +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(fullName, that.fullName) && Objects.equals(phone, that.phone) && Objects.equals(workAddress, that.workAddress) && Objects.equals(homeAddress, that.homeAddress) && Objects.equals(car, that.car) && Objects.equals(workDays, that.workDays) && Objects.equals(takenRides, that.takenRides) && Objects.equals(drivenRides, that.drivenRides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, fullName, phone, workAddress, homeAddress, car, workDays, takenRides, drivenRides);
    }
}
