package com.carpool.service.impl;

import com.carpool.dto.NotificationDto;
import com.carpool.entity.NotificationEntity;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.mapper.NotificationEntityDtoMapper;
import com.carpool.repository.NotificationRepository;
import com.carpool.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationEntityDtoMapper notificationEntityDtoMapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationEntityDtoMapper notificationEntityDtoMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationEntityDtoMapper = notificationEntityDtoMapper;
    }

    @Override
    public void addRideRequest(TakenRideEntity takenRide) {
        RideEntity ride = takenRide.getRide();
        String message = "User " + takenRide.getUser().getUsername() + " requested to join your ride " + rideDetails(ride);
        NotificationEntity notificationEntity = new NotificationEntity(takenRide, ride.getDriver(), message, true);
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void addRideApproved(TakenRideEntity takenRide) {
        RideEntity ride = takenRide.getRide();
        String message = "Driver " + ride.getDriver().getUsername() + takenRide.getApprovedString()+"your ride request " + rideDetails(ride);
        NotificationEntity notificationEntity = new NotificationEntity(takenRide, takenRide.getUser(), message, false);
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void addCancelTakenRide(TakenRideEntity takenRide) {
        deleteForeignKeyFromExistingNotifications(takenRide);
        RideEntity ride = takenRide.getRide();
        String message = "User " + takenRide.getUser().getUsername() + " canceled his ride request " + rideDetails(ride);
        NotificationEntity notificationEntity = new NotificationEntity(null, ride.getDriver(), message, false);
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void addCancelRide(List<TakenRideEntity> takenRides) {
        if (takenRides.isEmpty()) return;
        RideEntity ride = takenRides.get(0).getRide();
        String message = "Driver " + ride.getDriver().getUsername() + " canceled his ride " + rideDetails(ride);
        List<NotificationEntity> notifications = new ArrayList<>();
        for (TakenRideEntity takenRide : takenRides) {
            deleteForeignKeyFromExistingNotifications(takenRide);
            notifications.add(new NotificationEntity(null, takenRide.getUser(), message, false));
        }
        notificationRepository.saveAll(notifications);
    }

    private void deleteForeignKeyFromExistingNotifications(TakenRideEntity takenRide) {
        List<NotificationEntity> notifications =
                notificationRepository.findAll()
                .stream().filter(notification -> notification.getTakenRide()!=null && notification.getTakenRide().equals(takenRide))
                .collect(Collectors.toList());
        List<NotificationEntity> notificationsWithoutForeignKeys =
                notifications
                        .stream()
                        .map(notification ->
                                new NotificationEntity(notification.getId(), null, notification.getReceiver(), notification.getMessage(), notification.getDateTime(), false))
                        .collect(Collectors.toList());
        notificationRepository.saveAll(notificationsWithoutForeignKeys);
    }

    @Override
    public List<NotificationDto> getNotificationsForUser(Long userId) {
        return notificationRepository.findAllByReceiver_Id(userId)
                .stream()
                .sorted(Comparator.comparing(NotificationEntity::getDateTime).reversed())
                .map(notification -> notificationEntityDtoMapper.toDto(notification)).collect(Collectors.toList());
    }


    private String rideDetails(RideEntity ride) {
        return "from " + ride.getFrom().getStreet() + " " + ride.getFrom().getNumber() +
                " to " + ride.getTo().getStreet() + " " + ride.getTo().getNumber() + " on " + ride.getDateTime().toString();
    }
}
