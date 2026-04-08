package org.example.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.model.Split;
import org.example.model.User;

public class EqualSplitStrategy implements SplitStrategy {
    
    @Override
    public List<Split> split(double totalAmount,List<User> participants,Map<User,Double> metadata){
        double share = totalAmount/participants.size();
        List<Split> splits = new ArrayList<>();

        for(User user:participants)
        {
          splits.add(new Split(user, share));
        }
        return splits;
    }

}
