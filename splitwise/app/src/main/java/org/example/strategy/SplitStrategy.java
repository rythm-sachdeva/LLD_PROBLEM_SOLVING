package org.example.strategy;

import java.util.List;
import java.util.Map;

import org.example.model.Split;
import org.example.model.User;

public interface SplitStrategy {
    
    List<Split> split(double totalAmount,List<User> participants,Map<User,Double> metadata);
}
