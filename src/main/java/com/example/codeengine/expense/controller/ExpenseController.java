package com.example.codeengine.expense.controller;

import com.example.codeengine.expense.controllerApi.ExpensesApi;
import com.example.codeengine.expense.model.Expense;
import com.example.codeengine.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/")
public class ExpenseController implements ExpensesApi {
    @Autowired
    private ExpenseRepository expenseRepository;

//    public ExpenseController(ExpenseRepository expenseRepository) {
//        this.expenseRepository = expenseRepository;
//    }

    @Override
    public ResponseEntity<List<Expense>> getExpenseUsingGET() {
        return ResponseEntity.ok().body(expenseRepository.findAll());
    }

    @Override
    public ResponseEntity<Expense> deleteExpenseUsingDELETE(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Expense> createExpensesUsingPOST(@Valid @RequestBody Expense expense) {
        Expense result = expenseRepository.save(expense);
        try {
            return ResponseEntity.created(new URI("api/expenses" + result.getId())).body(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
