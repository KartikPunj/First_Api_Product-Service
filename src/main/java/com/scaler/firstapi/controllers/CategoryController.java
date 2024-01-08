package com.scaler.firstapi.controllers;

import com.scaler.firstapi.dtos.FakeStoreCategoryDTO;
import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryName}")
    public List<FakeStoreProductDTO> getAllProductsInCategory(@PathVariable("categoryName") String categoryName){
        return categoryService.getAllProductsInCategory(categoryName);
    }

    @GetMapping()
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
