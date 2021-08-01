package com.carpool.service;

import java.util.Optional;

import com.carpool.dto.TakenRideDto;

public interface TakenRideService {

	public Optional<TakenRideDto> update(TakenRideDto takenRide) throws Exception;

}
