package org.example.factory;

import org.example.enums.SplitType;
import org.example.strategy.EqualSplitStrategy;
import org.example.strategy.PercentageSplitStrategy;
import org.example.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType splitType)
    {
        return switch(splitType){
           case EQUAL ->  new EqualSplitStrategy();
           case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}
