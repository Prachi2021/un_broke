package com.prachi.un_broke.service;


import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class CategoryService {
     @Autowired
     CategoryRepo categoryRepo;

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoriesById(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category createCategory(@RequestBody Category category){
        return categoryRepo.save(category);
    }

}
