package org.example.model;

import org.example.enums.VehicleType;

import lombok.Getter;


@Getter
public abstract class Vehicle {

    private final String number;
    private final VehicleType vehicleType;
    
    public Vehicle(String number,VehicleType vehicleType)
    {
        this.number = number;
        this.vehicleType = vehicleType;
    }
    
}