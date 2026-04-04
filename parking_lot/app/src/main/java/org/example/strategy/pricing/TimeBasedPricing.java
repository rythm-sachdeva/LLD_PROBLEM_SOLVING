package org.example.strategy.pricing;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.example.enums.VehicleType;

public class TimeBasedPricing implements pricingStrategy{
    
    private static final LocalTime PEAK_START = LocalTime.of(8,0);
    private static final LocalTime  PEAK_END = LocalTime.of(17,0);
   
    private boolean isPeakHour(LocalTime time)
    {
        return !time.isBefore(PEAK_START) && !time.isAfter(PEAK_END);
    }

    @Override
    public double calculateFee(VehicleType vehicleType,LocalDateTime entryTime,LocalDateTime exitTime)
    {

        if (exitTime.isBefore(entryTime)) throw new IllegalArgumentException("Exit time before entry time");

        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil(durationMinutes / 60.0);

        int peakHours = 0;
        int offpeakHours= 0;
        LocalDateTime cursor = entryTime.truncatedTo(ChronoUnit.HOURS);
         for (int i = 0; i < totalHours; i++) {
            LocalTime hourStart = cursor.toLocalTime();
            if (isPeakHour(hourStart)) peakHours++;
            else offpeakHours++;
            cursor = cursor.plusHours(1);
        }

        double peakRate = switch (vehicleType) {
            case CAR -> 15.0;
            case BIKE -> 7.0;
            case TRUCK -> 25.0;
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };

        double offpeakRate = switch (vehicleType) {
            case CAR -> 10.0;
            case BIKE -> 5.0;
            case TRUCK -> 20.0;
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };

        return (peakHours * peakRate) + (offpeakHours * offpeakRate);
        
    }
}
