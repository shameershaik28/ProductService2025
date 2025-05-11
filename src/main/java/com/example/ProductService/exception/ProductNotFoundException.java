package com.example.ProductService.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

}
