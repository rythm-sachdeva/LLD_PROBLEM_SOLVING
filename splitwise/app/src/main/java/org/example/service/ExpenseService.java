package org.example.service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import org.example.enums.SplitType;
import org.example.factory.SplitStrategyFactory;
import org.example.model.Expense;
import org.example.model.Group;
import org.example.model.User;
import org.example.strategy.SplitStrategy;
import org.example.model.Split;

@AllArgsConstructor
public class ExpenseService {
    private final BalanceSheetService balanceSheetService;

    public void addExpense(Group group,String description,double amount,User paidBy, List<User> participants,SplitType splitType,Map<User,Double>metaData)
    {
        SplitStrategy strategy = SplitStrategyFactory.getStrategy(splitType);
        List<Split> splits = strategy.split(amount, participants, metaData);
         Expense expense = new Expense(description, amount, paidBy, splits, splitType);
         group.addExpense(expense);


         balanceSheetService.updateBalances(group, paidBy, splits);

    }


}
