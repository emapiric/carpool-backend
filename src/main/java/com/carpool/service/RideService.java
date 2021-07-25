package com.carpool.service;

import java.util.List;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;

public interface RideService {

    List<RideDto> search(RideRequestDto rideRequest);
    List<RideDto> findAll();
    List<RideDto> findUpcomingByUserId(Long userId);
	RideDto save(RideDto rideDto) throws Exception;
    void deleteRide(int id) throws Exception;
}
