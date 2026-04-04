package org.example.factory;

import org.example.enums.PaymentMode;
import org.example.strategy.payment.CardPayment;
import org.example.strategy.payment.CashPayment;
import org.example.strategy.payment.PaymentStrategy;
import org.example.strategy.payment.UpiPayment;


public class PaymentStrategyFactory {

    public static PaymentStrategy get(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}
