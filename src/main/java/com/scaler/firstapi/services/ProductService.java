package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.exceptions.ProductNotExistsException;
import com.scaler.firstapi.models.Product;

import java.util.List;

public interface ProductService {
//    FakeStore Product Service methods


    void deleteProduct(Long id);  // delete a single product by id

    Product addNewProduct(Product product);  // add a single product by id

    public List<Product> getAllProducts();  // get all products

    public Product replaceProduct(Long id, Product product);  // replace a single product by id
//
    public FakeStoreProductDTO updateProduct(Long id, Product product);  // update a single product by id

    //SelfProduct Service methods
    Product getSingleProduct(Long id) throws ProductNotExistsException;  // get a single product by id


    public Product updateSelfProduct(Long id, Product product) throws ProductNotExistsException;  // update a single product by id

    public boolean deleteSelfProduct(Long id) throws ProductNotExistsException;
}
