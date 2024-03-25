package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.CustomUserDetails;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.service.CategoryService;
import com.prachi.un_broke.service.ExpenseService;
import com.prachi.un_broke.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<Expense>> getExpenses(@AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        List<Expense> expenseList = expenseService.getExpenses(userId);
        return ResponseEntity.ok(expenseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        Expense expense = expenseService.getExpenseById(id, userId);
        return ResponseEntity.ok(expense);
    }

    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense_DTO edto, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        Expense expense = expenseService.createExpense(edto, userId);
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense_DTO edto, @PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        return ResponseEntity.ok(expenseService.updateExpense(edto, id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Expense>> deleteExpense(@PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        expenseService.deleteExpense(id);
        return getExpenses(userPrincipal);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Object>> getExpensesWithCategory(@AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        List<Object> expenses = expenseService.getExpensesWithCategory(userId);
        return ResponseEntity.ok(expenses);
    }
}
