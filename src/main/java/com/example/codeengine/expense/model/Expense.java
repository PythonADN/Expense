package com.example.codeengine.expense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Расходы
 * Одна статья расходов может иметь одного пользователя и одну катерию
 */
@Entity
@Table(name = "expense")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {
    // Id, Date, Description, UserId, CategoryId
    @Id
    private Long id;

    private Instant expenseDate;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
