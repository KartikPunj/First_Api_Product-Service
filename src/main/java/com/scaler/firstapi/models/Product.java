package com.scaler.firstapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Category category;
    private String description;
    private String imageUrl;
    private Integer no_of_sales;
}
