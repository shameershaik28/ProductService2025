package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID id) throws ProductNotFoundException;
    public Product createProduct(String name, String category, String description);
    List<Product> getAllProducts();
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException ;
    void deleteProduct(UUID id) throws ProductNotFoundException;
}
