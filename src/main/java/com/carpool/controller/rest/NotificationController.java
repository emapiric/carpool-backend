package com.carpool.controller.rest;

import com.carpool.dto.NotificationDto;
import com.carpool.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<NotificationDto>> getNotificationsForUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotificationsForUser(userId));
    }

    @PutMapping("{notificationId}")
    public ResponseEntity<NotificationDto> setNotificationToAnswered(@PathVariable Long notificationId) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.setNotificationToAnswered(notificationId));
    }
}
