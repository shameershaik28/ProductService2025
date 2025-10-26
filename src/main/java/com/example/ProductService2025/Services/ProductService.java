package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;

public interface ProductService {
    public Product getProductById(long id) throws ProductNotFoundException;
    public Product createProduct(String name, String category, String description);
}
