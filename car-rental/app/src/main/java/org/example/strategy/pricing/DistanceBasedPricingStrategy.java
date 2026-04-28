package org.example.strategy.pricing;

import java.time.LocalDateTime;

import org.example.model.Vehicle;

public class DistanceBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle ,LocalDateTime start,LocalDateTime end,double distanceKm)
    {
        return distanceKm * vehicle.getPricePerKm();
    }
    
}
