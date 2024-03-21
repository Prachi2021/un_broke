package com.prachi.un_broke.model;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    private String description;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catId", referencedColumnName = "id")
    SubCategory subCategory;


    public Expense(){}
    public Expense(String desc, double amt, SubCategory subCategory, Date date){
        this.amount = amt;
        this.description= desc;
        this.subCategory = subCategory;
        this.date = date;
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
}
