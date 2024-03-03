package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreCategoryDTO;
import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    public List<FakeStoreProductDTO> getAllProductsInCategory(String categoryName);

    public List<String> getAllCategories();

    List<Category> getAllCategoriesSelf();

    List<Product> getAllProductsInCategorySelf(String categoryName);

    Category addNewCategory(Category category);

//    public Category save(Category category);
}
