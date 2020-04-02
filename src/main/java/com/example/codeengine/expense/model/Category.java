package com.example.codeengine.expense.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Категории
 * Одна категория может иметь одного пользователя (и множество расходов)
 */
@Entity
@Table(name = "category")
@NoArgsConstructor
@Data
public class Category {
    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    //    // каскад на сохранение
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private User user;
}
