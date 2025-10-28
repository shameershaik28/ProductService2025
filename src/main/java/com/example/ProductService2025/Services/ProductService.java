package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id) throws ProductNotFoundException;
    public Product createProduct(String name, String category, String description);
    List<Product> getAllProducts();
    public Product updateProduct(Long id, String name, String category, String description) throws ProductNotFoundException ;
    void deleteProduct(Long id) throws ProductNotFoundException;

}
