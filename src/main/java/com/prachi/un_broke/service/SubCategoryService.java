package com.prachi.un_broke.service;


import com.prachi.un_broke.LoggerClass;
import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.SubCategory;
import com.prachi.un_broke.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.prachi.un_broke.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    public List<SubCategory> getSubCategories(int user_id) {
        if(subCategoryRepo!=null)
            return subCategoryRepo.findByUserId(user_id);
        else {
            LoggerClass.provideError("------------------Sub Category List is null------------------");
            return null;
        }
    }
    public SubCategory getSubCategoryById(int id, int user_id) {
        return subCategoryRepo.findById(id).orElse(null);
    }

    public SubCategory createSubCategory(Category_DTO cdto, int user_id){
        Category cat = categoryService.getCategoryById(cdto.getCat_id());
        User user = userService.getUserById(user_id);
        if(cat != null) {
            SubCategory subCategory = new SubCategory(user, cat, cdto.getSubcategory());
            return subCategoryRepo.save(subCategory);
        }
        else {
            LoggerClass.provideError("------------------Category is null------------------");
            return null;
        }
    }

    public SubCategory updateSubCategory(Category_DTO cdto, int id, int user_id){
        Category cat = categoryService.getCategoryById(cdto.getCat_id());
        if(cat != null) {
            SubCategory subCategory = getSubCategoryById(id, user_id);
            if(subCategory != null){
                subCategory.setSubCategory(cdto.getSubcategory());
                subCategory.setCategory(cat);
                return subCategoryRepo.save(subCategory);
            }
            else return null;
        }
        else return null;
    }

    public void deleteSubCategory(int id, int userId){
        SubCategory subCat = getSubCategoryById(id, userId);
        if(subCat != null)
            subCategoryRepo.delete(subCat);
    }

    public List<Object> getSubcatWithCat(@RequestParam int userId) {
        List<Object> arrSubcatsWithCat =  new ArrayList<>();
        for(SubCategory subCategory : getSubCategories(userId)){
            List<Object> subcatWithCat =  new ArrayList<>();
            subcatWithCat.add(subCategory.getId());
            subcatWithCat.add(subCategory.getSubCategory());
            subcatWithCat.add(subCategory.getCategory().getCategory());
            arrSubcatsWithCat.add(subcatWithCat);
        }
        return arrSubcatsWithCat;
    }
}
