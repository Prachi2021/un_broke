package com.prachi.un_broke.service;


import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.CategoryRepo;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class SubCategoryService {
     @Autowired
     SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public List<SubCategory> getSubCategories() {
        return subCategoryRepo.findAll();
    }
    public SubCategory getSubCategoriesById(int id) {
        return subCategoryRepo.findById(id).orElse(null);
    }

    public SubCategory createSubCategory(@RequestBody Category_DTO dto){
        Category category = categoryRepo.findById(dto.getCat_id()).orElse(null);
        if (category == null) throw new RuntimeException();
        else {
            SubCategory subCategory = new SubCategory(dto.getSub_category(), dto.getCategory());
            return subCategoryRepo.save(subCategory);
        }
    }

}
