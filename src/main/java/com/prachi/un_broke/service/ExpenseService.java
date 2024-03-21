package com.prachi.un_broke.service;


import com.prachi.un_broke.LoggerClass;
import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.CategoryRepo;
import com.prachi.un_broke.repository.ExpenseRepo;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;
    @Autowired
    SubCategoryService subCategoryService;

    public List<Expense> getExpenses() {
        return expenseRepo.findAll();
    }

    public Expense getExpenseById(int id) {
        return expenseRepo.findById(id).orElse(null);
    }

    public Expense createExpense(Expense_DTO edto) {
        SubCategory subCat = subCategoryService.getSubCategoryById(edto.getCat_id());
        edto.setSubcategory(subCat);
        Expense expense = new Expense(edto.getDescription(), edto.getAmount(),  new java.sql.Date(new Date().getTime()), subCat);
        return expenseRepo.save(expense);
    }

    public Expense updateExpense(Expense_DTO edto, int id){
        Expense expense = getExpenseById(id);
        if(edto.getDate() != null)
            expense.setDate(edto.getDate());
        else
            expense.setDate(expense.getDate());
        expense.setAmount(edto.getAmount());
        expense.setDescription(edto.getDescription());
        SubCategory subCat = subCategoryService.getSubCategoryById(edto.getCat_id());
        expense.setSubCategory(subCat);

        return expenseRepo.save(expense);

    }

    public void deleteExpense(int id){
        expenseRepo.deleteById(id);
    }

   /* public List<Expense_DTO> getExpensesWithCategory() {
        return expenseRepo.getExpensesWithCategory();
    }*/
}
