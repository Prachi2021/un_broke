package com.prachi.un_broke.controller;

import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(categories == null || categories.isEmpty())
            return new ResponseEntity<>(categories, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
        Category category = categoryService.getCategoryById(id);
        if(category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category cat = categoryService.createCategory(category);
        if(cat != null)
            return new ResponseEntity<>(cat, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") int id){
        Category cat = categoryService.updateCategory(category, id);
        if(cat == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable("id") int id){
        List<Category> categories = categoryService.deleteCategory(id);
        if(categories != null)
            return new ResponseEntity<>(categories, HttpStatus.OK) ;
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
