package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.service.CategoryService;
import com.prachi.un_broke.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub_categories")
public class SubCategoryServiceController {
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<SubCategory>> getSubCategories(){
        List<SubCategory> subcategories = subCategoryService.getSubCategories();
        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoriesById(@PathVariable("id") int id) {
        SubCategory subCat = subCategoryService.getSubCategoriesById(id);
        return ResponseEntity.ok(subCat);
    }

    @PostMapping("")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody Category_DTO cdto) {
        SubCategory subCat = subCategoryService.createSubCategory(cdto);
        return ResponseEntity.ok(subCat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody Category_DTO cdto, @PathVariable("id") int id) {
        SubCategory subCat = subCategoryService.updateSubCategory(cdto, id);
        return ResponseEntity.ok(subCat);
    }

    @DeleteMapping("/{id}")
    public List<SubCategory> deleteSubCategory(@PathVariable("id") int id){
        subCategoryService.deletSubCategory(id);
        return getSubCategories();
    }
}
