package com.example.codeengine.expense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Пользователь
 * Один пользователь может иметь множество категорий (и множество расходов)
 */
@Entity
@Table(name = "user")
@AllArgsConstructor // создать конструктор со всеми полями
@NoArgsConstructor // создать дефолтный конструктор
@Data // создать гетеры, сетеры, toString, ...
public class User {
    @Id // перчичный ключ
    private Long id;

    private String name;

    private String email;

    // пользователь может иметь много категорий One-User, Many-Category
//    @OneToMany // для исключения дубликатов
//    private Set<Category> categories;
}
