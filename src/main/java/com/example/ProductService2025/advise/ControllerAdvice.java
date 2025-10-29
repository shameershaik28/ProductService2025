package com.example.ProductService2025.advise;

import com.example.ProductService2025.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    // ✅ Handle case when product is not found (valid UUID, but not in DB)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    // ✅ Handle case when invalid UUID format is passed (e.g., /api/products/123)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>("Invalid product ID format. Expected UUID.", HttpStatus.BAD_REQUEST); // 400
    }
}
