package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID id) throws ProductNotFoundException;

    Optional<ProductInfo> getProductInfo(UUID id);


    public Product createProduct(String name, String category, String description);
    List<Product> getAllProducts();
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException ;
    void deleteProduct(UUID id) throws ProductNotFoundException;

    Optional<ProductNameOnly> getProductName(UUID id);
}
