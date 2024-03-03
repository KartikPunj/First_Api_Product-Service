package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    private CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<FakeStoreProductDTO> getAllProductsInCategory(String categoryName) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }

    @Override
    public List<Category> getAllCategoriesSelf() {
       return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsInCategorySelf(String categoryName) {
        return categoryRepository.findByCategory_Name(categoryName);
    }

    @Override
    public Category addNewCategory(Category category) {
        return null;
    }
}
