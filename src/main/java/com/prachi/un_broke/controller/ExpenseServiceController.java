package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.service.CategoryService;
import com.prachi.un_broke.service.ExpenseService;
import com.prachi.un_broke.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseServiceController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ExpenseService expenseService;

    @GetMapping("/api/expenses")
    public ResponseEntity<List<Expense>> getExpenses(){
        List<Expense> expenseList = expenseService.getExpenses();
        return ResponseEntity.ok(expenseList);
    }
    @PostMapping("/api/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense_DTO dto){
        Expense expense = expenseService.createExpense(dto);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/api/expenses_categories")
    public ResponseEntity<List<Expense_DTO>> getExpensesWithCategory(){
        List<Expense_DTO> expenses = expenseService.getExpensesWithCategory();
        return ResponseEntity.ok(expenses);
    }
}
