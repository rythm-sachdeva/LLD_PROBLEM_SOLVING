package org.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.model.Booking;

public class BookingRepository {
    private final Map<String,Booking> bookings = new HashMap<>();

    public void addBooking(Booking booking)
    {
        bookings.put(booking.getBookingId(),booking);

    }

    public Optional<Booking> getBookingById(String id)
    {
        return Optional.ofNullable(bookings.get(id));
    }

    public void removeBooking(String bookingId){
        Booking booking = bookings.remove(bookingId);
        if(booking !=null)
        {
            booking.getVehicle().getIsBooked().set(false);
        }
    }
    public List<Booking> getAllBookings(){
        return new ArrayList<>(bookings.values());
    }
}
