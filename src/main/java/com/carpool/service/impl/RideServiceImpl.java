package com.carpool.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.carpool.entity.TakenRideEntity;
import com.carpool.service.NotificationService;
import com.carpool.service.TakenRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.mapper.RideEntityDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.service.HaversineService;
import com.carpool.service.RideService;

@Service
@Transactional
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;
    private RideEntityDtoMapper rideMapper;
    private HaversineService haversineService;
    private TakenRideService takenRideService;
    NotificationService notificationService;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, RideEntityDtoMapper rideMapper, HaversineService haversineService, TakenRideService takenRideService, NotificationService notificationService) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
        this.haversineService = haversineService;
        this.takenRideService = takenRideService;
        this.notificationService = notificationService;
    }

    @Override
    public List<RideDto> search(RideRequestDto rideRequest) {
        return rideRepository
                .findAll()
                .stream().filter(ride -> haversineService.isOnWay(ride, rideRequest) &&
                        isLaterToday(ride.getDateTime(), rideRequest.getDateTime()) &&
                        ride.hasSpace(rideRequest.getNumberOfPassangers()))
                .map(ride -> rideMapper.toDto(ride)).collect(Collectors.toList());
    }

    private boolean isLaterToday(LocalDateTime rideTime, LocalDateTime rideRequestTime) {
        return rideTime.getYear() == rideRequestTime.getYear() &&
                rideTime.getMonth() == rideRequestTime.getMonth() &&
                rideTime.getDayOfMonth() == rideRequestTime.getDayOfMonth() &&
                (rideTime.isAfter(rideRequestTime) || rideTime.isEqual(rideRequestTime));
    }

    @Override
    public List<RideDto> findUpcomingByUserId(Long userId) {
        return rideRepository
                .findAll()
                .stream().filter(ride -> ride.getDriver().getId() == userId &&
                        ride.getDateTime().isAfter(LocalDateTime.now()))
                .map(ride -> rideMapper.toDto(ride)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> findUpcomingTakenRidesByUserId(Long userId) {
        List<Long> rideIds = takenRideService.findFutureApprovedByUserId(userId);
        return rideRepository
                .findAll()
                .stream().filter(ride -> rideIds.contains(ride.getId()))
                .map(ride -> rideMapper.toDto(ride)).collect(Collectors.toList());
    }

    @Override
    public void deleteRide(int id) throws Exception {
        List<TakenRideEntity> takenRides = takenRideService.findAllByRide((long) id);
        notificationService.addCancelRide(takenRides);
        rideRepository.deleteById((long) id);
    }


}