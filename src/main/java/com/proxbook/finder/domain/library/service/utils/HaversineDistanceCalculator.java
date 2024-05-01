package com.proxbook.finder.domain.library.service.utils;

import org.springframework.stereotype.Component;

@Component
public class HaversineDistanceCalculator implements DistanceCalculator{
    private static double EARTH_RADIUS = 6371.0; // km

    private double haversine(double val){
        return Math.pow(Math.sin(val / 2), 2);
    }

    @Override
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLon = Math.toRadians(longitude2- longitude1);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.pow(Math.sin(dLon / 2), 2);
        double centralAngle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * centralAngle;

        return distance;
    }
}
