package com.prachi.un_broke.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "id")
    private Set<SubCategory> subCategories;

    public Category(){}
    public Category(int id, String category){
        this.id = id;
        this.category = category;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
