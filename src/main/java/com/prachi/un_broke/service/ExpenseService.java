package com.prachi.un_broke.service;


import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.model.Mode;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.model.User;
import com.prachi.un_broke.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    UserService userService;
    @Autowired
    ModeService modeService;

    public List<Expense> getExpenses(int user_id) {
        return expenseRepo.findByUserId(user_id);
    }

    public Expense getExpenseById(int id, int user_id) {
        return expenseRepo.findExpenseByIdAndUserId(id, user_id);
    }

    public Expense createExpense(Expense_DTO edto, int user_id) {
        SubCategory subCat = subCategoryService.getSubCategoryById(edto.getCat_id(), user_id);
        User user = userService.getUserById(user_id);
        Mode mode = modeService.getModeById(edto.getMode().getModeId(), user.getUser_id());
        if(subCat != null) {
            edto.setSubcategory(subCat);
            edto.setUser(user);
            Expense expense = new Expense(user, edto.getDescription(), edto.getAmount(), new java.sql.Date(new Date().getTime()), subCat, mode);
            return expenseRepo.save(expense);
        }
        else
            return null;
    }

    public Expense updateExpense(Expense_DTO edto, int id, int user_id){
        Expense expense = getExpenseById(id, user_id);
        if(expense != null) {
            if (edto.getDate() != null)
                expense.setDate(edto.getDate());
            else
                expense.setDate(expense.getDate());
            expense.setAmount(edto.getAmount());
            expense.setDescription(edto.getDescription());
            expense.setMode(edto.getMode());
            SubCategory subCat = subCategoryService.getSubCategoryById(edto.getCat_id(), edto.getUser_id());
            expense.setSubCategory(subCat);

            return expenseRepo.save(expense);
        }
        else
            return null;
    }

    public void deleteExpense(int id){
        expenseRepo.deleteById(id);
    }

    public List<Object> getExpensesWithCategory(int user_id) {
        List<Object> expensesWithCategories = new ArrayList<>();
        for(Expense expense: getExpenses(user_id)){
            List<Object> expenseWithCategory = new ArrayList<>();
            expenseWithCategory.add(expense.getDate());
            expenseWithCategory.add(expense.getDescription());
            expenseWithCategory.add(expense.getAmount());
            expenseWithCategory.add(expense.getSubCategory().getSubCategory());
            expenseWithCategory.add(expense.getSubCategory().getCategory().getCategory());
            expensesWithCategories.add(expenseWithCategory);
        }
        return expensesWithCategories;
    }
}
