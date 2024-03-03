package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.exceptions.ProductNotExistsException;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.repositories.CategoryRepository;
import com.scaler.firstapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;



    @Autowired
    public SelfProductService( ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistsException {
        Product product;
        try {
            Product productOptional = productRepository.findByid(id);
            if (productOptional == null) {
                throw new ProductNotExistsException("Product with id " + id + " not found");
            }
            product = productOptional;
        }
        catch (ProductNotExistsException e){
            throw e;
        }

        return product;
    }

    @Override
    public Product updateSelfProduct(Long id, Product product) throws ProductNotExistsException {
        Optional<Product> productOptional= Optional.ofNullable(productRepository.findByid(id));
        if(productOptional.isEmpty()){
            throw new ProductNotExistsException("Product with id "+ id + " not found");
        }
        Product savedProduct= productOptional.get();
        if(product.getTitle()!=null){
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null){
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null){
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getCategory()!=null){
            Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()));
            if(categoryOptional.isEmpty()){
                savedProduct.setCategory(categoryRepository.save(product.getCategory()));
            }
            else{
                savedProduct.setCategory(categoryOptional.get());
            }
        }
        if(product.getImageUrl()!=null){
            savedProduct.setImageUrl(product.getImageUrl());
        }


        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product addNewProduct(Product product) {
        // check if category exists or not if not then save it with same name and id
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()));
        if(categoryOptional.isEmpty()){
          //product.setCategory(categoryRepository.save(product.getCategory()));
            // Here we have used cascade type persist in category entity so we don't need to save category explicitly
        }
        else{
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }

    @Override
    public  List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public  boolean deleteSelfProduct(Long id) throws ProductNotExistsException {
        Optional<Product> productOptional= Optional.ofNullable(productRepository.findByid(id));
        if(productOptional.isEmpty()){
            throw new ProductNotExistsException("Product with id "+ id + " not found");
        }
        productRepository.delete(productOptional.get());
        return true;
    }

    @Override
    public FakeStoreProductDTO updateProduct(Long id, Product product) {
        return null;
    }
}
