package com.prachi.un_broke.service;


import com.prachi.un_broke.LoggerClass;
import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryService categoryService;

    public List<SubCategory> getSubCategories() {
        if(subCategoryRepo!=null)
            return subCategoryRepo.findAll();
        else {
            LoggerClass.provideError("------------------Sub Category List is null------------------");
            return null;
        }
    }
    public SubCategory getSubCategoryById(int id) {
        return subCategoryRepo.findById(id).orElse(null);
    }

    public SubCategory createSubCategory(Category_DTO cdto){
        Category cat = categoryService.getCategoryById(cdto.getCat_id());
        if(cat != null) {
            SubCategory subCategory = new SubCategory(cat, cdto.getSubcategory());
            return subCategoryRepo.save(subCategory);
        }
        else {
            LoggerClass.provideError("------------------Category is null------------------");
            return null;
        }
    }

    public SubCategory updateSubCategory(Category_DTO cdto, int id){
        Category cat = categoryService.getCategoryById(cdto.getCat_id());
        if(cat != null) {
            SubCategory subCategory = getSubCategoryById(id);
            if(subCategory != null){
                subCategory.setSubCategory(cdto.getSubcategory());
                subCategory.setCategory(cat);
                return subCategoryRepo.save(subCategory);
            }
            else return null;
        }
        else return null;
    }

    public void deleteSubCategory(int id){
        SubCategory subCat = getSubCategoryById(id);
        if(subCat != null)
            subCategoryRepo.delete(subCat);
    }

    public List<Object> getSubcatWithCat(){
        List<SubCategory> subCategories = getSubCategories();
        List<Object> arrSubcatsWithCat =  new ArrayList<>();
        for(SubCategory subCategory : subCategories){
            List<Object> subcatWithCat =  new ArrayList<>();
            subcatWithCat.add(subCategory.getId());
            subcatWithCat.add(subCategory.getSubCategory());
            subcatWithCat.add(subCategory.getCategory().getCategory());
            arrSubcatsWithCat.add(subcatWithCat);
        }
        return arrSubcatsWithCat;
    }
}
