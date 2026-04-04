package org.example.model;

import org.example.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String number){
        super(number, VehicleType.CAR);
    }
}