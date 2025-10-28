package com.example.ProductService2025.Controllers;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.Services.ProductService;
import com.example.ProductService2025.dtos.CreateProductRequestDto;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @Qualifier("dbImpl")
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID productId) throws ProductNotFoundException {
//        if(productId<1 || productId>20){
//              return new ResponseEntity<>(HttpStatusCode.valueOf(400));
//        }
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.valueOf(200));
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
