package com.carpool.service;

import java.util.List;
import java.util.Optional;

import com.carpool.dto.TakenRideDto;

public interface TakenRideService {

	public Optional<TakenRideDto> update(TakenRideDto takenRide) throws Exception;

    public List<Long> findFutureApprovedByUserId(Long userId);
}
