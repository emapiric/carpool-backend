package com.carpool.service;

import java.util.List;
import java.util.Optional;

import com.carpool.dto.TakenRideDto;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;

public interface TakenRideService {

    Optional<TakenRideDto> update(TakenRideDto takenRide) throws Exception;
	List<Long> findFutureApprovedByUserId(Long userId);
    List<TakenRideEntity> findAllByRide(Long rideId);
    TakenRideEntity findByUserAndRide(UserEntity user, RideEntity ride);

}
