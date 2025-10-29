package com.example.ProductService2025.repositories;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.projections.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findFirstByNameAndCategory(String name, String category);
    List<Product> findAllByCategory(String category);
    Optional<Product> findById(UUID id);
    List<Product> findAllByNameLike(String word);

    List<Product> findAllByNameLikeIgnoreCase(String word);
    List<Product> findAllByIdLessThan(UUID id);


    @Query("SELECT p.id AS id, p.name AS name, p.description AS description FROM products p WHERE p.id = :id")
    Optional<ProductInfo> getProductInfo(@Param("id") UUID id);

    @Query("SELECT p FROM products p WHERE p.id = :id")
    <T> Optional<T> findProductById(@Param("id") UUID id, Class<T> type);










}
