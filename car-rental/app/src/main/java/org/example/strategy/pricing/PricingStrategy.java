package org.example.strategy.pricing;

import java.time.LocalDateTime;

import org.example.model.Vehicle;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle,LocalDateTime start,LocalDateTime end,double distanceKm);
}
