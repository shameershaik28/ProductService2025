package com.example.ProductService.Repository;

import com.example.ProductService.dto.ProductProjection;
import com.example.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{
    List<Product> findAllByDescription(String description);
    List<Product> findAllByDescriptionIgnoreCase(String description);
    Product findFirstByDescriptionIgnoreCase(String description);
    ProductProjection findFirstByName(String name);
    ProductProjection findByNameAndDescription(String name, String description);
}
