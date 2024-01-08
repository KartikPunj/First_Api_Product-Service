package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;


    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImage());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
       FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

        assert fakeStoreProductDTO != null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        //delete the product from the database
        restTemplate.delete (
                "https://fakestoreapi.com/products/" + id
        );
        System.out.println("Product with id: " + id + " deleted successfully");

    }

    @Override
    public Product addNewProduct(Product product){
        //add the product to the database using third party API
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product);

        FakeStoreProductDTO response = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                POST,
                new HttpEntity<>(fakeStoreProductDTO),
                FakeStoreProductDTO.class

        ).getBody();


        return convertFakeStoreProductDtoToProduct(response);
    }


    //Method to convert Product to FakeStoreProductDTO to add product to database
    private static FakeStoreProductDTO getFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductDTO;
    }

    @Override
    public List<Product> getAllProducts() {
        //return all the products
        FakeStoreProductDTO[] response =restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
        );

        List<Product> ans = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : response){
            ans.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDTO));
        }

        return ans;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //update the product in the database using put method
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO,FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" +id, PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);
    }

    public Product updateProduct(Long id, Product product) {
        //update the product in the database using patch method with the help of third party API
        // patch will only update the fields that are passed in the request body and rest fields will remain same
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        if(product.getTitle() != null)
            fakeStoreProductDTO.setTitle(product.getTitle());
        if(product.getPrice() != 0)
            fakeStoreProductDTO.setPrice(product.getPrice());
        if(product.getDescription() != null)
            fakeStoreProductDTO.setDescription(product.getDescription());
        if(product.getImageUrl() != null)
            fakeStoreProductDTO.setImage(product.getImageUrl());
        if(product.getCategory().getName() != null)
            fakeStoreProductDTO.setCategory(product.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);


//        FakeStoreProductDTO response = restTemplate.exchange(
//                    "https://fakestoreapi.com/products/" + id,
//                    PATCH,
//                    new HttpEntity<>(fakeStoreProductDTO),
//                    FakeStoreProductDTO.class
//
//            ).getBody();
//
//            return convertFakeStoreProductDtoToProduct(response);

    }

}
