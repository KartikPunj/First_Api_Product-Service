package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreCategoryDTO;
import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;

import java.util.List;

public interface CategoryService {
    public List<FakeStoreProductDTO> getAllProductsInCategory(String categoryName);

    public List<String> getAllCategories();
}
