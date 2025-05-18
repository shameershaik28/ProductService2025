package com.example.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReqDTO {
    private String name;
    private String description;
    private int categoryId;
    private double price;
    private double rating;
    private int quantity;
}
