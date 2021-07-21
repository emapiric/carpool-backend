package com.carpool.service.impl;

import com.carpool.service.HaversineService;
import org.springframework.stereotype.Service;

@Service
public class HaversineServiceImpl implements HaversineService {

    final int R = 6371;

    @Override
    public double toRad(double value) {
        return value*Math.PI/180;
    }

    @Override
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = toRad(lat2-lat1);
        double lonDistance = toRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;
        return distance;
    }
}
