package com.example.ProductService.controller;

import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.service.ProductService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
