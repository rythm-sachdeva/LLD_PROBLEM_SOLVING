package org.example.strategy.booking;

import java.util.List;

import org.example.enums.PricingStrategyType;
import org.example.model.Vehicle;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CheapestBookingStrategy implements BookingStrategy {
  private final PricingStrategyType pricingType;
    
    @Override
    public Vehicle bookVehicle(List<Vehicle> vehicles)
    {
        List<Vehicle> sortedVehicles = vehicles.stream().sorted(
            (v1,v2)->{
                double val1 = pricingType == PricingStrategyType.TIME_BASED ? v1.getPricePerHour() :v1.getPricePerKm();
                double val2 = pricingType == PricingStrategyType.DISTANCE_BASED ? v2.getPricePerHour() : v2.getPricePerKm();
                return Double.compare(val1, val2); 
            }
        ).toList();

      for(Vehicle v: sortedVehicles){
        if(v.getIsBooked().compareAndSet(false, true))
        {
            return v;
        }
      }
      return null;

    }
    
}
