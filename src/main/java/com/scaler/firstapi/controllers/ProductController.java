package com.scaler.firstapi.controllers;

import com.scaler.firstapi.commons.AuthenticationCommons;
import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.dtos.Role;
import com.scaler.firstapi.dtos.UserDto;
import com.scaler.firstapi.exceptions.ProductNotExistsException;
import com.scaler.firstapi.models.Product;
import com.scaler.firstapi.services.ProductService;
import com.scaler.firstapi.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController //Hey spring in this class im going to implement a rest API
@RequestMapping("/products") //we write the common path for all the methods in this class
public class ProductController {


    private ProductService productService;
    private RestTemplate restTemplate;

    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate,AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.authenticationCommons = authenticationCommons;
    }
    @GetMapping()//localhost:8080/products
    public ResponseEntity<List<Product>> getAllProducts(){
        // earlier we are passing the token as string @RequestHeader("AuthenticationToken") String token not this took care by spring security
//        UserDto userDto = authenticationCommons.validateToken(token);
//        if(userDto == null){
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        boolean isAdmin = false;
//        for(Role role: userDto.getRoles()){
//            if(role.getName().equals("ADMIN")){
//                isAdmin = true;
//                break;
//            }
//        }
//        if(isAdmin){
//            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        }


        //return all the products
        List<Product> products = productService.getAllProducts(); // o p q

//        List<Product> finalProducts = new ArrayList<>();
//
//        for (Product p: products) { // o  p q
//            p.setTitle("Hello " + p.getTitle());
//            finalProducts.add(p);
//        }
//
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(
                //this is the response entity that we are sending back instead of the product list
                //so that we can send additional information like status code etc with response
                //productService.getAllProducts(),
                products,
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistsException {

        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        //add the product to the database
        return productService.addNewProduct(product);

    }

    @PatchMapping("/{id}") // update an object partially
    public FakeStoreProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        //update the product in the database
        return productService.updateProduct(id,product);

    }

    @PutMapping("/{id}") // update an object completely i.e to replace
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        //update the product in the database
        return productService.replaceProduct(id,product);

    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) throws ProductNotExistsException {
//       productService.deleteProduct(id);
        if(productService.deleteSelfProduct(id)){
            return "Product with id "+ id + " deleted successfully";
        }
        else{
            return "Product with id "+ id + " not found";
        }
    }
    @PatchMapping("/self/{id}")
    public Product updateSelfProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotExistsException {
        return productService.updateSelfProduct(id,product);
    }


}
