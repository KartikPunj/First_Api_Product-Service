package com.scaler.firstapi.services;

import com.scaler.firstapi.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);  // get a single product by id

    void deleteProduct(Long id);  // delete a single product by id

    Product addNewProduct(Product product);  // add a single product by id

    public List<Product> getAllProducts();  // get all products

    public Product replaceProduct(Long id, Product product);  // replace a single product by id

    public Product updateProduct(Long id, Product product);  // update a single product by id
}
