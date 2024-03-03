package com.scaler.firstapi.repositories;

import  com.scaler.firstapi.models.Category;
import com.scaler.firstapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);

    List<Category> findAll();

    //Using HQL query to fetch all products in a category using parameter categoryName
    //linking of parameter categoryName with the categoryName in the query is done using @Param annotation
    @Query("select p from Product p where p.category.name =  :categoryName")
    List<Product> findByCategory_Name(@Param("categoryName") String categoryName);
}
