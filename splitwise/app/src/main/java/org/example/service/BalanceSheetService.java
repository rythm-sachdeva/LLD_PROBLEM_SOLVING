package org.example.service;

import java.util.List;

import org.example.model.Group;
import org.example.model.Split;
import org.example.model.User;

public class BalanceSheetService {
    
    public void updateBalances(Group group,User paidBy,List<Split> splits){
        double  totalAmount = splits.stream().mapToDouble(Split::getAmount).sum();
        group.getBalanceSheet(paidBy).addTotalPaid(totalAmount);

        for(Split split: splits)
        {
            User user = split.getUser();
            double amt = split.getAmount();

            group.getBalanceSheet(user).addTotalExpense(amt);

            if(user.equals(paidBy))
            {
                group.getBalanceSheet(user).addBalance(paidBy, -amt);
                group.getBalanceSheet(paidBy).addBalance(user, amt);
            }
        }

    }

   
}
