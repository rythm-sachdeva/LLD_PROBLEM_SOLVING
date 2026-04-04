package org.example.strategy.pricing;

import java.time.LocalDateTime;
import org.example.enums.VehicleType;


public interface pricingStrategy {
double calculateFee(VehicleType vehicleType,LocalDateTime  entryTime,LocalDateTime exitTime);
}