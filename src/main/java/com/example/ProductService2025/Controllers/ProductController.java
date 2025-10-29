package com.example.ProductService2025.Controllers;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.Services.ProductService;
import com.example.ProductService2025.dtos.CreateProductRequestDto;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @Qualifier("dbImpl")
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID productId) throws ProductNotFoundException {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<?> getProductInfo(@PathVariable UUID id) {
        Optional<ProductInfo> opt = productService.getProductInfo(id);

        if (opt.isPresent()) {
            ProductInfo info = opt.get();
            return ResponseEntity.ok(info);
        } else {
            String msg = "Product not found with id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<?> getNameOnly(@PathVariable UUID id) {
        Optional<ProductNameOnly> opt = productService.getProductName(id);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            String msg = "Product not found with id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto) {
        System.out.println(requestDto);
        return productService.createProduct(requestDto.getName(), requestDto.getCategory(),
                requestDto.getDescription());
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody CreateProductRequestDto dto) throws ProductNotFoundException {

        Product updated = productService.updateProduct(id, dto.getName(), dto.getCategory(), dto.getDescription());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable UUID id) throws ProductNotFoundException {
        Product existing = productService.getProductById(id); // will throw if not found
        productService.deleteProduct(id);
        return ResponseEntity.ok(existing); // return the deleted entity
    }
}
