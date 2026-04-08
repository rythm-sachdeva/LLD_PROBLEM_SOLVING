package org.example.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.example.model.Split;
import org.example.model.User;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> split(double totalAmount,List<User> participants,Map<User,Double> metadata)
    {
        double totalPercent = metadata.values().stream().mapToDouble(Double::doubleValue).sum();
        if(totalPercent != 100.0) throw new IllegalStateException("Total Percentage should be less than 100");
        List<Split> splits = new ArrayList<>();

        for(User user: participants)
        {
            splits.add(new Split(user, totalAmount * metadata.getOrDefault(user, 0.0)/100));
        }
        return splits;
    }
    
}
