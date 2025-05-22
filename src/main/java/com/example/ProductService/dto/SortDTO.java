package com.example.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SortDTO {
    private String filterName;
    private boolean filterType; // true - asc, false - desc
}
