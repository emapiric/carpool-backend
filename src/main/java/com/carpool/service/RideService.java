package com.carpool.service;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.dto.TakenRideDto;
import com.carpool.entity.RideEntity;

import java.util.List;

public interface RideService {

    List<RideDto> search(RideRequestDto rideRequest);
    List<RideDto> findAll();
    List<RideDto> findUpcomingByUserId(Long userId);
	RideDto save(RideDto rideDto) throws Exception;
    void deleteRide(int id) throws Exception;
}
