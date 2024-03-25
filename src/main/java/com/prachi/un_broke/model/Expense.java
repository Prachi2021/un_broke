package com.prachi.un_broke.model;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "mode", referencedColumnName = "mode_id")
    private Mode mode;

    public Expense(){}
    public Expense(User user_id, String desc, double amt, Date date, SubCategory subCategory, Mode mode_id){
        this.user_id = user_id;
        this.description= desc;
        this.amount = amt;
        this.date = date;
        this.subCategory = subCategory;
        this.mode = mode_id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public SubCategory getSubCategory() {
        return subCategory;
    }
    public String getDescription() {
        return description;
    }
    public Date getDate() {
        return date;
    }
    public User getUser_id() {
        return user_id;
    }
    public Mode getMode() {
        return mode;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    public void setAmount(double amt) {
        this.amount = amt;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
    public void setMode(Mode mode_id) {
        this.mode = mode_id;
    }
}
