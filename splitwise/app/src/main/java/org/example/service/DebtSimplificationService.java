package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.example.model.BalanceSheet;
import org.example.model.Group;
import org.example.model.User;

public class DebtSimplificationService {
    
    public void simplyfyDebts(Group group)
    {
        List<User> users = new ArrayList<>(group.getMembers());
        Map<User,BalanceSheet> sheets = group.getBalanceSheets();

        Map<User,Double> netBalances = new HashMap<>();
        for(User user:users)
        {
         double net = 0.0;
         Map<User,Double> balances = sheets.get(user).getBalances();

         for(double amount: balances.values())
         {
            net += amount;
         }
         netBalances.put(user, net);
        }

        PriorityQueue<User> creditors = new PriorityQueue<>((a,b)->Double.compare(netBalances.get(b),netBalances.get(a)));
        PriorityQueue<User> debtors = new PriorityQueue<>((a,b)->Double.compare(netBalances.get(a), netBalances.get(b)));

        for (User user : users) {
            double net = netBalances.get(user);
            if (net > 0) {
                creditors.offer(user);
            } else if (net < 0) {
                debtors.offer(user);
            }
        }

        while(!creditors.isEmpty() && debtors.isEmpty())
        {
            User creditor = creditors.poll();
            User debtor = debtors.poll();


            double creditAmount = netBalances.get(creditor);
            double debitAmount = netBalances.get(debtor);
            double settledAmount = Math.min(creditAmount, -debitAmount);
             sheets.get(creditor).addBalance(debtor, settledAmount);
            sheets.get(debtor).addBalance(creditor, -settledAmount);
            netBalances.put(creditor, creditAmount - settledAmount);
            netBalances.put(debtor, debitAmount + settledAmount);
              if (netBalances.get(creditor) > 0) {
                creditors.offer(creditor);
            }
            if (netBalances.get(debtor) < 0) {
                debtors.offer(debtor);
            }
        }

    }
}
