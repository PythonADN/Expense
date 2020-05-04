package com.example.codeengine.expense.controller;

import com.example.codeengine.expense.controllerApi.CategoryApi;
import com.example.codeengine.expense.model.Category;
import com.example.codeengine.expense.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CategoryController implements CategoryApi {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>> categoriesUsingGET() {
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<Category> categoryByIdUsingGET(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Category> createCategoryUsingPOST(@Valid Category category) {
        Category result = categoryRepository.save(category);
        try {
            return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Category> deleteCategoryUsingDELETE(Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Category> updateCategoryUsingPUT(@Valid Category category) {
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

}
