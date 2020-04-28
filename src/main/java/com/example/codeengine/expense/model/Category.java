package com.example.codeengine.expense.model;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
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
//@ApiModel(description = "Категория - описание категории")
public class Category {
    @Id
//    @ApiModelProperty(notes = "идентификатор категории", example = "101")
    private Long id;

//    @ApiModelProperty(notes = "имя категории", example = "ADN")
    private String name;

    public Long getId() {
        return id;
    }

    //    // каскад на сохранение
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private User user;
}
