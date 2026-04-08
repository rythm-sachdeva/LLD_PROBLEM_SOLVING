package org.example.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.example.enums.SplitType;
import org.example.model.BalanceSheet;
import org.example.model.Group;
import org.example.model.User;
import org.example.repository.GroupRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepo;
    private final ExpenseService expenseService;
    private final DebtSimplificationService simplifier;

    public String createGroup(String name,List<User> members)
    {
        String id = UUID.randomUUID().toString();
        Group g = new Group(id, name);
        members.forEach(g::addMember);
        groupRepo.save(g);
        return id;
    }
    private Group get(String id)
    {
        return groupRepo.findById(id).orElseThrow(()->new IllegalStateException("Group Not Found " + id));
    }
     
   public void printBalances(String groupId) {
        Group g = get(groupId);
        g.getMembers().forEach(u -> {
            BalanceSheet sheet = g.getBalanceSheet(u);

            double owe = 0, get = 0;
            for (double v : sheet.getBalances().values()) {
                if (v < 0) owe += -v; else get += v;
            }
            System.out.printf("""
                               💵 %s
                               Paid: %.2f  Expense: %.2f
                               You owe: %.2f, You get: %.2f%n""",
                    u.getName(), sheet.getTotalPaid(),
                    sheet.getTotalExpense(), owe, get);

            sheet.getBalances().forEach((other, val) -> System.out.printf(
                    "  %s %.2f %s%n",
                    val > 0 ? "← get" : "→ owe",
                    Math.abs(val),
                    other.getName()));
            System.out.println("--------------------------");
        });
    }

    public void simplifyDebts(String id)
    {
        simplifier.simplyfyDebts(get(id));
    }

     public void addExpense(String groupId,
                           String description, double amount,
                           User paidBy, List<User> participants,
                           SplitType splitType, Map<User, Double> meta) {

        expenseService.addExpense(get(groupId), description, amount,
                paidBy, participants, splitType, meta);
    }




}
