package com.example.ProductService.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericException extends RuntimeException{
    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }
}
