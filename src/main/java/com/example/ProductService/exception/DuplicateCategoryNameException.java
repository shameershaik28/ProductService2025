package com.example.ProductService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateCategoryNameException extends RuntimeException{
    public DuplicateCategoryNameException() {
    }

    public DuplicateCategoryNameException(String message) {
        super(message);
    }
}
