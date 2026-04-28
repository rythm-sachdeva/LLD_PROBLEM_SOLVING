package org.example.strategy.payment;

import org.example.model.Booking;

public interface PaymentStrategy {
  
    boolean processPayment(Booking booking);
    
} 