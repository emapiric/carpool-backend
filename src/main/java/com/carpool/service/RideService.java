package com.carpool.service;

import java.util.List;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;

public interface RideService {

    List<RideDto> search(RideRequestDto rideRequest);
    List<RideDto> findUpcomingByUserId(Long userId);
    List<RideDto> findUpcomingTakenRidesByUserId(Long userId);
    void deleteRide(int id) throws Exception;
}
