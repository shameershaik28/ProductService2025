package com.example.ProductService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
