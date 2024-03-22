package com.prachi.un_broke;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import com.prachi.un_broke.controller.CategoryServiceController;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(CategoryServiceController.class)
public class CategoryControllerTests {
    // Category Controller Tests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    // Check if categories are retrieved upon GET method of url and status is OK
    @Test
    void get_all_categories_list() throws Exception {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "long term savings"));
        mockCategories.add(new Category(2, "short term savings"));
        when(categoryService.getCategories()).thenReturn(mockCategories);
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    // Check if categories list retrieved is empty, upon GET method of url and status is NO CONTENT
    @Test
    void get_empty_categories_list() throws Exception {
        List<Category> mockCategories = new ArrayList<>();
        when(categoryService.getCategories()).thenReturn(mockCategories);
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").isEmpty());
    }

    // Check if categories list retrieved is null, upon GET method of url and status is NO CONTENT
    @Test
    void get_null_categories_list() throws Exception {
        when(categoryService.getCategories()).thenReturn(null);
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    // Check if category is returned when category is found by Id
    @Test
    void get_category_by_id_found() throws Exception{
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "long term savings"));
        when(categoryService.getCategoryById(1)).thenReturn(mockCategories.get(0));
        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.category").value("long term savings"));
    }

    // Check if category is null if category is not found by Id
    @Test
    void get_category_by_id_not_found() throws Exception{
        when(categoryService.getCategoryById(1)).thenReturn(null);
        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isNotFound());
    }

    // Check if category is created successfully
    @Test
    void create_category_successful() throws Exception{
        Category category = new Category(1, "needs");
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isCreated());
    }

    // Check if category is updated successfully
    @Test
    void update_category_successful() throws Exception{
        Category category =  new Category(1, "Needs");
        Category category2 =  new Category(1, "Wants");
        when(categoryService.updateCategory(any(Category.class), eq(1))).thenReturn(category2);
        mockMvc.perform(put("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(category2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.category").value("Wants"));
    }

    // Check if category needed to be updated exists
    @Test
    void update_category_failed_id_not_found() throws Exception{
        Category category =  new Category(1, "Needs");
        Category category2 =  new Category(1, "Wants");
        when(categoryService.updateCategory(any(Category.class), eq(2))).thenReturn(null);
        mockMvc.perform(put("/api/categories/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(category2)))
                .andExpect(status().isNotFound());
    }

    // Check if category is updated successfully
    @Test
    void delete_category_successful() throws Exception{
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "Needs"));
        mockCategories.add(new Category(2, "Wants"));
        when(categoryService.deleteCategory(eq(1))).thenReturn(mockCategories);
        mockMvc.perform(delete("/api/categories/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Check if category needed to be updated exists
    @Test
    void delete_category_failed_id_not_found() throws Exception{
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "Needs"));
        mockCategories.add(new Category(2, "Wants"));
        when(categoryService.deleteCategory(eq(3))).thenReturn(null);
        mockMvc.perform(delete("/api/categories/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
