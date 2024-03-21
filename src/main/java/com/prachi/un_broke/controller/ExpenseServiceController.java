package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.service.CategoryService;
import com.prachi.un_broke.service.ExpenseService;
import com.prachi.un_broke.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseServiceController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ExpenseService expenseService;

    @GetMapping("")
    public ResponseEntity<List<Expense>> getExpenses(){
        List<Expense> expenseList = expenseService.getExpenses();
        return ResponseEntity.ok(expenseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") int id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense_DTO edto){
        Expense expense = expenseService.createExpense(edto);
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense_DTO edto, @PathVariable("id") int id){
        return ResponseEntity.ok(expenseService.updateExpense(edto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Expense>> deleteExpense(@PathVariable("id") int id){
        expenseService.deleteExpense(id);
        return getExpenses();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Object>> getExpensesWithCategory(){
        List<Object> expenses = expenseService.getExpensesWithCategory();
        return ResponseEntity.ok(expenses);
    }
}
