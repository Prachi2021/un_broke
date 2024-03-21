package com.prachi.un_broke.controller;

import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryServiceController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category cat = categoryService.createCategory(category);
        return ResponseEntity.ok(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @PathVariable("id") int id){
        Category cat = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(cat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return getCategories();
    }
}
