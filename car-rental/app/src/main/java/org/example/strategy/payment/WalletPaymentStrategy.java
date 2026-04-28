package org.example.strategy.payment;

import org.example.model.Booking;

public class WalletPaymentStrategy implements PaymentStrategy  {
       @Override
    public boolean processPayment(Booking booking) {
        // Simulate wallet payment processing
        System.out.println("Processing wallet payment for booking: " + booking.getBookingId());
        return true;
    }
}
