package com.carpool.service;

public interface HaversineService {

    double toRad(double value);
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);


}
