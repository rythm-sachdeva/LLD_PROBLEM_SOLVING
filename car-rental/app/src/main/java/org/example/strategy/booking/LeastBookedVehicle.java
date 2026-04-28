package org.example.strategy.booking;

import java.util.Comparator;
import java.util.List;

import org.example.model.Vehicle;

public class LeastBookedVehicle implements BookingStrategy {

    public Vehicle bookVehicle(List<Vehicle> vehicles)
    {
        List<Vehicle> sortedVehicles = vehicles.stream().sorted(Comparator.comparingInt(Vehicle::getBookingCount)).toList();

        for (Vehicle vehicle : sortedVehicles) {
            // Attempt to atomically set isBooked from false to true
            if (vehicle.getIsBooked().compareAndSet(false, true)) {
                // Successfully booked
                return vehicle;
            }
            // else, this vehicle is already booked, try next
        }

        // No vehicle could be booked
        return null;
    }
    
}
