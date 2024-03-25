package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.CustomUserDetails;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.service.CategoryService;
import com.prachi.un_broke.service.SubCategoryService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<SubCategory>> getSubCategories(@RequestParam int userId){
        List<SubCategory> subcategories = subCategoryService.getSubCategories(userId);
        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoriesById(@PathVariable("id") int id, @RequestParam int userId) {
        SubCategory subCat = subCategoryService.getSubCategoryById(id, userId);
        return ResponseEntity.ok(subCat);
    }

    @PostMapping("")
    public ResponseEntity<SubCategory> createSubCategory(@AuthenticationPrincipal CustomUserDetails userPrincipal, @RequestBody Category_DTO cdto) {
        int user_id = userPrincipal.getId();
        SubCategory subCat = subCategoryService.createSubCategory(cdto, user_id);
        return ResponseEntity.ok(subCat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody Category_DTO cdto, @PathVariable("id") int id, @RequestParam int userId) {
        SubCategory subCat = subCategoryService.updateSubCategory(cdto, id, userId);
        return ResponseEntity.ok(subCat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<SubCategory>> deleteSubCategory( @PathVariable("id") int id, @RequestBody int userId){
        subCategoryService.deleteSubCategory(id, userId);
        return getSubCategories(userId);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Object>> getSubcatsWithCategories(@RequestBody int userId){
        return ResponseEntity.ok(subCategoryService.getSubcatWithCat(userId));
    }
}
