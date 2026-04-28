package org.example.model;

import java.util.concurrent.atomic.AtomicBoolean;

import org.example.enums.VehicleStatus;
import org.example.enums.VehicleType;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public abstract class Vehicle {
   private String licensePlate;
   private VehicleStatus status;
   private double  pricePerHour;
   private double pricePerKm;
   private VehicleType type;

   private int bookingCount;
   
   private final AtomicBoolean isBooked = new AtomicBoolean(false);

     public Vehicle(String licensePlate, double pricePerHour, double pricePerKm, VehicleType type) {
        this.licensePlate = licensePlate;
        this.status = VehicleStatus.AVAILABLE;
        this.pricePerHour = pricePerHour;
        this.pricePerKm = pricePerKm;
        this.type = type;
    }

    public void incrementBookingCount()
    {
      this.bookingCount++;
    }

}
