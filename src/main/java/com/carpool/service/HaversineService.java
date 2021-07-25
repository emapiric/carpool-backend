package com.carpool.service;

public interface HaversineService {

    public double toRad(double value);
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2);


}
