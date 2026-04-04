package org.example.strategy.pricing;

import org.example.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class EventBasedPricing implements pricingStrategy {
    
    
    private static final Map<VehicleType,Double> Event_Hourly_Rate = Map.of(
        VehicleType.CAR, 20.0,
        VehicleType.BIKE, 10.0,
        VehicleType.TRUCK, 30.0
    );

   @Override
    public double calculateFee(VehicleType vehicleType,LocalDateTime entryTime,LocalDateTime exitTime)
    {
        long DurationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long hours = (long)Math.ceil(DurationMinutes/60.0);
        double ratePerHour = Event_Hourly_Rate.getOrDefault(vehicleType, null);
        return ratePerHour * hours;
    }

}
