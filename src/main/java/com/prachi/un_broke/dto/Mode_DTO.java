package com.prachi.un_broke.dto;

import com.prachi.un_broke.model.Expense;
import com.prachi.un_broke.model.Mode;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.model.User;

import java.sql.Date;

public class Mode_DTO {
    private int id;
    private String mode;
    private Expense expense;
    private User user;


    public Mode_DTO(User user, int id, Expense expense, String mode){
        this.id = id;
        this.expense = expense;
        this.user = user;
        this.mode = mode;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getMode() {
        return mode;
    }
    public Expense getExpense() {
        return expense;
    }
    public User getUser() {
        return user;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setExpense(Expense expense) {
        this.expense = expense;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
}
