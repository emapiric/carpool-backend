package com.carpool.dto;


import java.time.LocalDateTime;
import java.util.Objects;

public class NotificationDto implements MyDto{
    private static final long serialVersionUID = 1L;
    private Long id;
    private TakenRideDto takenRide;
    private UserDto receiver;
    private String message;
    private LocalDateTime dateTime;
    private boolean isRideRequest;

    public NotificationDto(Long id, TakenRideDto takenRide, UserDto receiver, String message, LocalDateTime dateTime, boolean isRideRequest) {
        this.id = id;
        this.takenRide = takenRide;
        this.receiver = receiver;
        this.message = message;
        this.dateTime = dateTime;
        this.isRideRequest = isRideRequest;
    }

    public NotificationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TakenRideDto getTakenRide() {
        return takenRide;
    }

    public void setTakenRide(TakenRideDto takenRide) {
        this.takenRide = takenRide;
    }

    public UserDto getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDto receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isRideRequest() {
        return isRideRequest;
    }

    public void setRideRequest(boolean rideRequest) {
        isRideRequest = rideRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDto that = (NotificationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(takenRide, that.takenRide) && Objects.equals(receiver, that.receiver) && Objects.equals(message, that.message) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, takenRide, receiver, message, dateTime);
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", takenRide=" + takenRide +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
