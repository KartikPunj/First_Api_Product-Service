package com.scaler.firstapi.controllers;

import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController //Hey spring in this class im going to implement a rest API
@RequestMapping("/products") //we write the common path for all the methods in this class
public class ProductController {


    private   ProductService productService;
    private RestTemplate restTemplate;

    @Autowired
    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        //return all the products
        return   productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){
            return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        //add the product to the database
        return productService.addNewProduct(product);

    }

    @PatchMapping("/{id}") // update an object partially
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        //update the product in the database
        return productService.updateProduct(id,product);

    }

    @PutMapping("/{id}") // update an object completely i.e to replace
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        //update the product in the database
        return productService.replaceProduct(id,product);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}
