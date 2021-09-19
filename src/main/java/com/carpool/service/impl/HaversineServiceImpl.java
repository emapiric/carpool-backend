package com.carpool.service.impl;

import org.springframework.stereotype.Service;

import com.carpool.dto.RideRequestDto;
import com.carpool.entity.RideEntity;
import com.carpool.service.HaversineService;

@Service
public class HaversineServiceImpl implements HaversineService {

	final int R = 6371;
	final int maxRatio = 10;

	@Override
	public double toRad(double value) {
		return value * Math.PI / 180;
	}

	@Override
	public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double latDistance = toRad(lat2 - lat1);
		double lonDistance = toRad(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;
		return distance;
	}

	@Override
	public boolean isOnWay(RideEntity ride, RideRequestDto rideRequest) {
		return this.calculateDistance(ride.getFrom().getLatitude(), ride.getFrom().getLongtitude(),
				rideRequest.getFrom().getLatitude(), rideRequest.getFrom().getLongtitude()) < maxRatio
				&& this.calculateDistance(ride.getTo().getLatitude(), ride.getTo().getLongtitude(),
						rideRequest.getTo().getLatitude(), rideRequest.getTo().getLongtitude()) < maxRatio;
	}
}
