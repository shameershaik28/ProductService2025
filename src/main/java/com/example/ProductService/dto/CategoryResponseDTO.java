package com.example.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponseDTO {
    private String categoryName;
    private String categoryDescription;

}
