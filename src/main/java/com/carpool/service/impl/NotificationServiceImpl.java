package com.carpool.service.impl;

import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
import com.carpool.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyDriver(TakenRideEntity takenRide) throws FirebaseMessagingException {
        UserEntity driver = takenRide.getRide().getDriver();
        Message message = Message.builder()
                .setNotification(
                        new Notification("title","body")
                )
                .putData("payload1", "data1")
                .putData("payload2", "data2")
                .setToken(driver.getJwt())
                .build();
        FirebaseMessaging.getInstance().send(message);
    }
}
