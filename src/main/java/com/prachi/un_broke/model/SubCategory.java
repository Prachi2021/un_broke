package com.prachi.un_broke.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subcategory;


    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "id")
    private Set<Expense> expense;


    public SubCategory(){}
    public SubCategory(User user_id, Category category, String subcategory){
        this.user_id = user_id;
        this.category = category;
        this.subcategory = subcategory;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getSubCategory() {
        return subcategory;
    }
    public User getUser_id() {
        return user_id;
    }

    public Category getCategory() {
        return category;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
    public void setSubCategory(String sub_category) {
        this.subcategory = sub_category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
