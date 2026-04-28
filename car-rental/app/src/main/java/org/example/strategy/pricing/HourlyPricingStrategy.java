package org.example.strategy.pricing;

import java.time.Duration;
import java.time.LocalDateTime;

import org.example.model.Vehicle;

public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle,LocalDateTime start,LocalDateTime end,double distanceKm)
    {
        long minutes = Duration.between(start,end).toMinutes();
        long hours = (long) (Math.ceil(minutes/60.0));
        return vehicle.getPricePerHour() * hours;
    }
}
