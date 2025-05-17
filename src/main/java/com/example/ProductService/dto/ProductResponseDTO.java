package com.example.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
    private String productName;
    private String productDescription;
    private double productPrice;
    private double rating;


}
