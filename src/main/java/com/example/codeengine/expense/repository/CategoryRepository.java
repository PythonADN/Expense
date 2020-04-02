package com.example.codeengine.expense.repository;

import com.example.codeengine.expense.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // добавим свой метод JPA реализует автоматом (findBy + поле которое ищем)
    Category findByName(String name);
}
