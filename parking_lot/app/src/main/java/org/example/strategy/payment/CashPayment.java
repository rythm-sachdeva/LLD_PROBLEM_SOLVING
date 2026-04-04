package org.example.strategy.payment;

import org.example.model.Ticket;

public class CashPayment implements PaymentStrategy {
    
    @Override
    public boolean processPayment(Ticket ticket,double amount)
    {
        System.out.println("Paid Rs " + amount + " For ticket: " + ticket.getTicketId() + " via Cash" ); 
        return true;
    }

}
