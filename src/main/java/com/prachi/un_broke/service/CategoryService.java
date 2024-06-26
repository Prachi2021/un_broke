package com.prachi.un_broke.service;


import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {
     @Autowired
     CategoryRepo categoryRepo;

    public List<Category> getCategories() {
        if(categoryRepo!=null)
            return categoryRepo.findAll();
        else
            return null;
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category createCategory(Category category){
        Category cat = categoryRepo.findById(category.getId()).orElse(null);
        if(cat == null)
            return categoryRepo.save(category);
        else
            return null;
    }

    public Category updateCategory(Category category, int id) {
        Category cat = getCategoryById(id);
        if (cat != null){
            cat.setCategory(category.getCategory());
            return categoryRepo.save(cat);
        }
        else
            return null;

    }
    public List<Category> deleteCategory(int id){
        Category cat = getCategoryById(id);
        if(cat != null) {
            categoryRepo.delete(cat);
            return getCategories();
        }
        else
            return null;
    }

}
