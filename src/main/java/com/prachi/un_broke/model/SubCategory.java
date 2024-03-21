package com.prachi.un_broke.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subcategory;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catId", referencedColumnName = "id")
    Category category;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "id")
    private Set<Expense> expense;


    public SubCategory(){}
    public SubCategory(String sub_category, Category category){
        this.category = category;
        this.subcategory = sub_category;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getSubCategory() {
        return subcategory;
    }
    public Category getCategory() {
        return category;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setSubCategory(String sub_category) {
        this.subcategory = sub_category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
