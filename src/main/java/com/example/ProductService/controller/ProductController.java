package com.example.ProductService.controller;

import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.service.ProductService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
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
    public FakeStoreProductDTO getProductById(@PathVariable("id") int id)
    {
        return productService.getProductById(id);
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


}
