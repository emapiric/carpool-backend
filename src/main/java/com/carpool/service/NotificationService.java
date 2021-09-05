package com.carpool.service;

import com.carpool.entity.TakenRideEntity;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void notifyDriver(TakenRideEntity takenRide) throws FirebaseMessagingException;
}
