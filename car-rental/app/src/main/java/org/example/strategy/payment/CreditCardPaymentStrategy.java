package org.example.strategy.payment;

import org.example.model.Booking;

public class CreditCardPaymentStrategy implements PaymentStrategy {
     @Override
    public boolean processPayment(Booking booking) {
        // Simulate credit card processing
        System.out.println("Processing credit card payment for booking: " + booking.getBookingId());
        return true;
    }
}
