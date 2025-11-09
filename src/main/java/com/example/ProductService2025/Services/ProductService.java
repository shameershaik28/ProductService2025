package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID id) throws ProductNotFoundException;
    // NEW: overload for numeric IDs (FakeStore)
    Product getProductById(Long id) throws ProductNotFoundException;

    Optional<ProductInfo> getProductInfo(UUID id);


    public Product createProduct(String name, String category, String description);
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException ;
    void deleteProduct(UUID id) throws ProductNotFoundException;

    Optional<ProductNameOnly> getProductName(UUID id);
    public Page<Product> getAllProducts(int pageSize, int pageNum);
}
