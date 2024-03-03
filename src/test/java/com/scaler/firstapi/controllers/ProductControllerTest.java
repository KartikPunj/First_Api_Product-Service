package com.scaler.firstapi.controllers;

import com.scaler.firstapi.exceptions.ProductNotExistsException;
import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.repositories.ProductRepository;
import com.scaler.firstapi.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
class ProductControllerTest {

//    @Autowired
//    private ProductController productController;
//    @Qualifier("selfProductService")
//    @MockBean
//    private ProductService productService;
//    @MockBean
//    private ProductRepository productRepository;
//        @Test
//    void onePlusOne() {
//            //arrange
//            //nothing to arrange
//            //act
//            int i=1+1;
//            //assert
//            assert 2==i;
//            assert i*i==4;
//            //A test case fails if any of the assert statements are false
//            assertEquals(2, i);
//    }

//    @Test
//    void testGetAllProducts() {
//        // this test case is to test the getAllProducts method of the product controller
//        // we are testing if the method returns all the products
//        // we are mocking the productService to return a list of products and then we are checking if the response contains the same list of products
//        //arrange
//        List<Product> productsList = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setId(1L);
//        p1.setTitle("Product 1");
//        productsList.add(p1);
//
//        Product p2 = new Product();
//        p2.setId(2L);
//        p2.setTitle("Product 2");
//        productsList.add(p2);
//
//        Product p3 = new Product();
//        p3.setId(3L);
//        p3.setTitle("Product 3");
//        productsList.add(p3);
//        // creating a new list of products to pass to the mock method to avoid pass by reference condition
//        List<Product> prodctsToPass = new ArrayList<>();
//        //copying the products from productsList to prodctsToPass
//        for (Product p : productsList) {
//            Product p11 = new Product();
//            p11.setTitle(p.getTitle());
//            prodctsToPass.add(p11);
//        }
//        when(
//                productService.getAllProducts()
//        ).thenReturn(
//                prodctsToPass
//        );
//        //act
//        ResponseEntity<List<Product>> response = productController.getAllProducts();
//
//        //assert
//        List<Product> productsInResponse = response.getBody();
//
//        assertEquals(productsList.size(), productsInResponse.size());
//        // loop to check if all the products in the list have same id and title as in response
//        // this test case passes even if I change the title of products in controller as the objects of arraylist acts as pass by reference
//        // so if I change the title of product in controller, it will also change in the response
//        for (int i = 0; i < productsInResponse.size(); ++i) {
//            //assertEquals(productsList.get(i).getId(), productsInResponse.get(i).getId());
//            assertEquals(productsList.get(i).getTitle(), productsInResponse.get(i).getTitle());
//        }
//    }
//

}