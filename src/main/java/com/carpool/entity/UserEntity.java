package com.carpool.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	@Column(name = "full_name")
	private String fullName;
	private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_number")
	private CarEntity car;
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RatingEntity> ratings = new ArrayList<RatingEntity>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<TakenRideEntity> takenRides = new ArrayList<TakenRideEntity>();
	@OneToMany(mappedBy = "driver", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<RideEntity> drivenRides = new ArrayList<RideEntity>();
	private Boolean enabled;
	private String confirmationToken;
	private String resetPasswordToken;

	public UserEntity(Long id, String username, String email, String password, String fullName, String phone,
			CarEntity car, List<RatingEntity> ratings, List<TakenRideEntity> takenRides, List<RideEntity> drivenRides,
			Boolean enabled, String confirmationToken, String forgotPasswordToken) {
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
		this.confirmationToken = confirmationToken;
		this.resetPasswordToken = forgotPasswordToken;
	}

	public UserEntity() {
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public String getForgotPasswordToken() {
		return resetPasswordToken;
	}

	public void setForgotPasswordToken(String forgotPasswordToken) {
		this.resetPasswordToken = forgotPasswordToken;
	}

	public Long getId() {
		return id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
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

	public void addTakenRide(TakenRideEntity takenRide) throws Exception {
		if (takenRides.contains(takenRide)) {
			throw new Exception("Taken ride already exist");
		} else {
			takenRides.add(takenRide);
		}
	}

	public void removeTakenRide(RideEntity ride) {
		for (Iterator<TakenRideEntity> iterator = takenRides.iterator(); iterator.hasNext();) {
			TakenRideEntity takenRideEntity = iterator.next();

			if (takenRideEntity.getUser().equals(this) && takenRideEntity.getRide().equals(ride)) {
				iterator.remove();
				takenRideEntity.getRide().getPassengers().remove(takenRideEntity);
				takenRideEntity.setUser(null);
				takenRideEntity.setRide(null);
			}
		}
	}

	@Override
	public String toString() {
		return "UserEntity{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\''
				+ ", password='" + password + '\'' + ", fullName='" + fullName + '\'' + ", phone='" + phone + '\''
				+ ", car=" + car + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserEntity))
			return false;
		UserEntity that = (UserEntity) o;
		return id == that.id && Objects.equals(username, that.username) && Objects.equals(email, that.email)
				&& Objects.equals(password, that.password) && Objects.equals(fullName, that.fullName)
				&& Objects.equals(phone, that.phone) && Objects.equals(car, that.car)
				&& Objects.equals(drivenRides, that.drivenRides);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email, password, fullName, phone, car, takenRides, drivenRides);
	}
}
