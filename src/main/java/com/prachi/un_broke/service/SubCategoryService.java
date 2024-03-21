package com.prachi.un_broke.service;


import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.CategoryRepo;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public List<SubCategory> getSubCategories() {
        if(subCategoryRepo!=null)
            return subCategoryRepo.findAll();
        else
            return null;
    }
    public SubCategory getSubCategoriesById(int id) {
        return subCategoryRepo.findById(id).orElse(null);
    }

    public SubCategory createSubCategory(Category_DTO cdto){
        Category cat = categoryRepo.findById(cdto.getCat_id()).orElse(null);
        if(cat != null) {
            SubCategory subCategory = new SubCategory(cat, cdto.getSub_category());
            return subCategoryRepo.save(subCategory);
        }
        else return null;
    }

    public SubCategory updateSubCategory(Category_DTO cdto){
        Category cat = categoryRepo.findById(cdto.getCat_id()).orElse(null);
        if(cat != null) {
            SubCategory subCategory = subCategoryRepo.findById(cdto.getId()).orElse(null);
            if(subCategory != null){
                subCategory.setSubCategory(cdto.getSub_category());
                subCategory.setCategory(cat);
                return subCategoryRepo.save(subCategory);
            }
            else return null;
        }
        else return null;
    }

    public void deletSubCategory(int id){
        SubCategory subCat = subCategoryRepo.findById(id).orElse(null);
        if(subCat != null)
            subCategoryRepo.delete(subCat);
    }

}
