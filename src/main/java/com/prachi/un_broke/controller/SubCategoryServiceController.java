package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubCategoryServiceController {
    @Autowired
    private SubCategoryService subCategoryService;
}

    @GetMapping("/sub_categories")
    public List<SubCategory> getSubCategories() {
        return subCategoryService.
    }
    public SubCategory getSubCategoriesById(int id) {
        return subCategory.findById(id).orElse(null);
    }

    public SubCategory createSubCategory(@RequestBody Category_DTO dto){
        Category category = categoryRepo.findById(dto.getCat_id()).orElse(null);
        if (category == null) throw new RuntimeException();
        else {
            SubCategory subCategory = new SubCategory(dto.getSub_category(), dto.getCategory());
            return subCategoryRepo.save(subCategory);
        }
}
