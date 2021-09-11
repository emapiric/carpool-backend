package com.carpool.service;

import com.carpool.dto.NotificationDto;
import com.carpool.entity.TakenRideEntity;

import java.util.List;

public interface NotificationService {

    void addRideRequest(TakenRideEntity takenRide);
    void addRideApproved(TakenRideEntity takenRide);
    void addCancelTakenRide(TakenRideEntity takenRide);
    void addCancelRide(List<TakenRideEntity> takenRides);
    List<NotificationDto> getNotificationsForUser(Long userId);
    NotificationDto setNotificationToAnswered(Long notificationId);
}
