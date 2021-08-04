package com.carpool.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.carpool.util.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private AddressDto workAddress;
	private AddressDto homeAddress;
	private CarDto car;
	private List<WorkingTimeDto> workDays = new ArrayList<WorkingTimeDto>();
	private List<RatingDto> ratings = new ArrayList<RatingDto>();
	private List<TakenRideDto> takenRides = new ArrayList<TakenRideDto>();
	private List<RideDto> drivenRides = new ArrayList<RideDto>();
	private Boolean enabled;
	private Provider provider;
	private CarpoolDto carpool;

	public UserDto(Long id, String username, String email, String password, String fullName, String phone,
			AddressDto workAddress, AddressDto homeAddress, CarDto car, List<WorkingTimeDto> workDays,
			List<RatingDto> ratings, List<TakenRideDto> takenRides, List<RideDto> drivenRides, Boolean enabled,
			Provider provider) {
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
		this.enabled = enabled;
		this.provider = provider;
	}

	public UserDto() {
	}

	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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

	public List<WorkingTimeDto> getWorkDays() {
		return workDays;
	}

	public void setWorkDays(List<WorkingTimeDto> workDays) {
		this.workDays = workDays;
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
	

	public CarpoolDto getCarpool() {
		return carpool;
	}

	public void setCarpool(CarpoolDto carpool) {
		this.carpool = carpool;
	}

	@Override
	public String toString() {
		return "UserDto{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", password='"
				+ password + '\'' + ", fullName='" + fullName + '\'' + ", phone='" + phone + '\'' + ", workAddress="
				+ workAddress + ", homeAddress=" + homeAddress + ", car=" + car + ", workDays=" + workDays
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
				&& Objects.equals(workAddress, userDto.workAddress) && Objects.equals(homeAddress, userDto.homeAddress)
				&& Objects.equals(car, userDto.car) && Objects.equals(workDays, userDto.workDays)
				&& Objects.equals(ratings, userDto.ratings) && Objects.equals(takenRides, userDto.takenRides)
				&& Objects.equals(drivenRides, userDto.drivenRides);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email, password, fullName, phone, workAddress, homeAddress, car, workDays,
				ratings, takenRides, drivenRides);
	}
}
