package com.prachi.un_broke.dto;

import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.SubCategoryRepo;

import java.sql.Date;

public class Expense_DTO {
    private int id;
    private String description;
    private double amount;
    private Date date;
    private int cat_id;  // sub category id
    private SubCategory subcategory;
    private int category_id; // category id
    private String category;



    public Expense_DTO(String description, double amount, Date date, int cat_id){
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.cat_id = cat_id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public double getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }
    public int getCat_id() {
        return cat_id;
    }
    public SubCategory getSubcategory(){
        return subcategory;
    }
    public String getCategory() {
        return category;
    }
    public int getCategory_id() {
        return category_id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    public void setAmount(double amt) {
        this.amount = amt;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setCat_id(int sub_cat_id) {
        this.cat_id = sub_cat_id;
    }
    public void setSubcategory(SubCategory sub_cat) {
        this.subcategory = sub_cat;
    }
    public void setCategory(String cat) {
        this.category = cat;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
