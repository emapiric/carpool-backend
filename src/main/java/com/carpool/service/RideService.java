package com.carpool.service;

import java.util.List;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.entity.RideEntity;

public interface RideService {

    List<RideDto> search(RideRequestDto rideRequest);
    List<RideDto> findAll();
    List<RideDto> findUpcomingByUserId(Long userId);
    List<RideDto> findUpcomingTakenRidesByUserId(Long userId);
    RideDto save(RideDto rideDto) throws Exception;
    void deleteRide(int id) throws Exception;
    RideEntity findRideEntityById(int rideId);
}
