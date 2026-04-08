

package org.example.model;

import java.util.List;

import org.example.enums.SplitType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public class Expense {
  private final String description;
  private final double amount;
  private final User paidBy;
  private final List<Split> splits;
  private final SplitType splitType;
}