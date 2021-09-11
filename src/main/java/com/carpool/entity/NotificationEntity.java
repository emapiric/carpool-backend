package com.carpool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification")
public class NotificationEntity implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="userId"),
            @JoinColumn(name="rideId")
    })
    private TakenRideEntity takenRide;
    @ManyToOne
    @JoinColumn(name="receiverId", referencedColumnName = "id")
    private UserEntity receiver;
    private String message;
    private LocalDateTime dateTime = LocalDateTime.now();
    private boolean isRideRequest;
    private boolean isAnswered;

    public NotificationEntity(Long id, TakenRideEntity takenRide, UserEntity receiver, String message, LocalDateTime dateTime, boolean isRideRequest, boolean isAnswered) {
        this.id = id;
        this.takenRide = takenRide;
        this.receiver = receiver;
        this.message = message;
        this.dateTime = dateTime;
        this.isRideRequest = isRideRequest;
        this.isAnswered = isAnswered;
    }

    public NotificationEntity(TakenRideEntity takenRide, UserEntity receiver, String message, boolean isRideRequest) {
        this.takenRide = takenRide;
        this.receiver = receiver;
        this.message = message;
        this.isRideRequest = isRideRequest;
    }

    public NotificationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TakenRideEntity getTakenRide() {
        return takenRide;
    }

    public void setTakenRide(TakenRideEntity takenRide) {
        this.takenRide = takenRide;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
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
