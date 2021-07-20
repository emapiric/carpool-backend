package com.carpool.service;

import com.carpool.dto.RideDto;

import java.util.List;

public interface RideService {

    List<RideDto> search(double fromLatitude, double fromLongtitude, double fromLocation, double toLocation);
    List<RideDto> findAll();
	RideDto save(RideDto rideDto) throws Exception;
}
