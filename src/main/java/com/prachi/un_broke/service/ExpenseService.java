package com.prachi.un_broke.service;


import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.CategoryRepo;
import com.prachi.un_broke.repository.ExpenseRepo;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;


@Service
public class ExpenseService {
     @Autowired
     ExpenseRepo expenseRepo;
    @Autowired
    SubCategoryRepo subCatRepo;
    @Autowired
    CategoryRepo catRepo;

    public List<Expense> getExpenses() {
        return expenseRepo.findAll();
    }

    public Expense createExpense(@RequestBody Expense_DTO dto){
        SubCategory subCat = subCatRepo.findById(dto.getCat_id()).orElse(null);
        if(subCat == null)
            throw new RuntimeException();
        else {
            dto.setSubcategory(subCat);
            Category cat = catRepo.findById(subCat.getCategory().getId()).orElse(null);
            if(cat == null)
                throw new RuntimeException();
            else {
                Expense expense = new Expense(dto.getDescription(), dto.getAmount(), subCat, new java.sql.Date(new Date().getTime()));
                return expenseRepo.save(expense);
            }
        }
    }

    public List<Expense_DTO> getExpensesWithCategory() {
        return expenseRepo.getExpensesWithCategory();
    }
}
