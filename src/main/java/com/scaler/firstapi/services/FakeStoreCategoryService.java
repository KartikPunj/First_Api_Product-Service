package com.scaler.firstapi.services;

import com.scaler.firstapi.dtos.FakeStoreCategoryDTO;
import com.scaler.firstapi.dtos.FakeStoreProductDTO;
import com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCategoryService implements CategoryService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ArrayList<FakeStoreProductDTO> getAllProductsInCategory(String categoryName) {
        //get all products from the database
        //filter them according to the category string passed
        //add them to list if the category matches
        //return the list
        FakeStoreProductDTO[] respose = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        ArrayList<FakeStoreProductDTO> list = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : respose){
            if(fakeStoreProductDTO.getCategory().equals(categoryName)){
                list.add(fakeStoreProductDTO);
            }
        }
        return list;
    }

    @Override
    public List<String> getAllCategories() {
        //get all categories from the database
        //As the response is an array of strings, so we made a string array and stored the response in it
        //then we converted the string array to an arraylist and returned it
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        ArrayList<String> list = new ArrayList<>();
            for(String s : response) {
                list.add(s);
            }
        return list;
    }

}
