package org.example.utils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.example.enums.BookingStatus;
import org.example.model.Booking;

public class VehicleAvailailityChecker {
    public static boolean isAvailable(List<Booking> bookings,LocalDateTime start,LocalDateTime end)
    {
        List<Booking> activeBookings =  bookings.stream().filter(b -> b.getStatus() == BookingStatus.CREATED || b.getStatus() == BookingStatus.CONFIRMED)
        .sorted(Comparator.comparing(Booking::getStartTime)).toList();

        for(Booking b :activeBookings)
        {
            LocalDateTime existingStart = b.getStartTime();
            LocalDateTime existingEnd = b.getEndTime();
              boolean overlaps = !(end.isBefore(existingStart) || start.isAfter(existingEnd));
            if (overlaps) {
                return false;
            }
        }
        return true;
    }
}
