package com.example.ProductService.controller;

import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.service.ProductService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public FakeStoreProductDTO[] getAllProducts()
    {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<FakeStoreProductDTO> getProductById(@PathVariable("id") int id)
    {
        if(id<=0)
        {
            throw  new IllegalArgumentException("Product does not exist");
            //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        FakeStoreProductDTO response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products")
    public FakeStoreProductDTO createProduct(@RequestBody FakeStoreProductDTO product)
    {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public FakeStoreProductDTO updateProduct(@PathVariable("id") int id, @RequestBody FakeStoreProductDTO product)
    {
        return productService.updateProduct(id, product);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
