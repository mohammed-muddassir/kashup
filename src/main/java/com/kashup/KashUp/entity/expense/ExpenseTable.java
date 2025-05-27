package com.kashup.KashUp.entity.expense;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "ExpenseTable")
public class ExpenseTable
{
    @Id
    private Long expenseID;

    private Long userCategoryID;

    private Long typeID;

    private BigDecimal expenseAmount;

    private Long accountID;

    private String notes;

}
