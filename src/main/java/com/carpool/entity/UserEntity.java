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
@Table(name="user")
public class UserEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity workAddress;
    @ManyToOne
    @JoinColumn(name="id")
    private AddressEntity homeAddress;
    @ManyToOne
    @JoinColumn(name="plate_number")
    private CarEntity car;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingTimeEntity> workDays = new ArrayList<WorkingTimeEntity>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TakenRideEntity> takenRides = new ArrayList<TakenRideEntity>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RideEntity> drivenRides = new ArrayList<RideEntity>();

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
