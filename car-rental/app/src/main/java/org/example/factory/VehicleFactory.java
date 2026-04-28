package org.example.factory;

import org.example.enums.VehicleType;
import org.example.model.SUV;
import org.example.model.Sedan;
import org.example.model.Vehicle;

public class VehicleFactory {

    public static Vehicle create(VehicleType type,String licensePlate,double pricePerHour,double pricePerKm)
    {
     return  switch (type) {
      case VehicleType.SEDAN ->  new Sedan(licensePlate, pricePerHour, pricePerKm); 
      case VehicleType.SUV -> new SUV(licensePlate, pricePerHour, pricePerKm);
    };
    }

    
}
