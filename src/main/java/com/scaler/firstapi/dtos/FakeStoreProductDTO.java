package com.scaler.firstapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDTO {

    private Long id;
    private String title;
    private Double price;
    private String Category;
    private String description;
    private String image;
}
