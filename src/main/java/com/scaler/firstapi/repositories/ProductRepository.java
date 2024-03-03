package com.scaler.firstapi.repositories;

import com.scaler.firstapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findByid (Long id);
    List<Product> findByTitleContaining(String title);

    Product save(Product product);

//    @Query("select p from Product p")
     List<Product> findAll();

}
