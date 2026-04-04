package org.example.factory;

import org.example.enums.VehicleType;
import org.example.model.Bike;
import org.example.model.Car;
import org.example.model.Truck;
import org.example.model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String number, VehicleType type) {
        return switch (type) {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }
}
