package com.carpool.service;

import com.carpool.dto.RideRequestDto;
import com.carpool.entity.RideEntity;

public interface HaversineService {

     double toRad(double value);
     double calculateDistance(double lat1, double lon1, double lat2, double lon2);
     boolean isOnWay(RideEntity ride, RideRequestDto rideRequest);


}
