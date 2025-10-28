package com.example.ProductService2025.repositories;

import com.example.ProductService2025.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findFirstByNameAndCategory(String name, String category);
    List<Product> findAllByCategory(String category);

}
