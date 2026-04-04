package org.example.strategy.payment;

import org.example.model.Ticket;

public interface PaymentStrategy { 
    boolean processPayment(Ticket ticket,double amount);
}
