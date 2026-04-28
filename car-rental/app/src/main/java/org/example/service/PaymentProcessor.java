package org.example.service;

import org.example.enums.PaymentStatus;
import org.example.model.Booking;
import org.example.strategy.payment.PaymentStrategy;

public class PaymentProcessor {
    
    private final PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }

   public boolean pay(Booking booking)
   {
    boolean success = paymentStrategy.processPayment(booking);

    if(success)
    {
        booking.setPaymentStatus(PaymentStatus.SUCCESS);
    }
    else{
        booking.setPaymentStatus(PaymentStatus.FAILED);
        System.out.println("Payment Failed!");
    }
    return success;

   }

}
