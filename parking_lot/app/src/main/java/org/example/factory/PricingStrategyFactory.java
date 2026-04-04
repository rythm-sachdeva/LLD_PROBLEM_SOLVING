package org.example.factory;
import org.example.enums.PricingStrategyType;
import org.example.strategy.pricing.EventBasedPricing;
import org.example.strategy.pricing.TimeBasedPricing;
import org.example.strategy.pricing.pricingStrategy;


public class PricingStrategyFactory {
    public static pricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}