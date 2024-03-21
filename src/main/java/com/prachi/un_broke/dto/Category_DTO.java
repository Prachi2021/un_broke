package com.prachi.un_broke.dto;

import com.prachi.un_broke.model.Category;


public class Category_DTO {
    private int id; // subcategory id
    private String sub_category; // subcategory name
    private int cat_id;  // category id
    private Category category; // category object

    // Constructors
    public Category_DTO(){}

    public Category_DTO(Category category, String subcategory){
        this.category = category;
        this.sub_category = subcategory;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getSub_category() {
        return sub_category;
    }
    public Category getCategory() {
        return category;
    }
    public int getCat_id() {
        return cat_id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
}
