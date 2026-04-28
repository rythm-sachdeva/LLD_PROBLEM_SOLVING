package org.example.model;

import org.example.enums.VehicleType;

public class SUV extends Vehicle {
    public SUV(String licensePlate, double pricePerHour, double pricePerKm){
        super(licensePlate, pricePerHour, pricePerKm, VehicleType.SUV);
    }
}

