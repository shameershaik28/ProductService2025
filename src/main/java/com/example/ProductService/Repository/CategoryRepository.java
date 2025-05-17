package com.example.ProductService.Repository;

import com.example.ProductService.model.Category;
import com.example.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    Optional<Category> findByProductsIn(List<Product> products);
}
