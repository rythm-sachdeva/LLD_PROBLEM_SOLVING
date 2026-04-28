package org.example.strategy.booking;

import java.util.List;

import org.example.model.Vehicle;

public interface BookingStrategy {
    Vehicle bookVehicle(List<Vehicle> vehicles);
}
