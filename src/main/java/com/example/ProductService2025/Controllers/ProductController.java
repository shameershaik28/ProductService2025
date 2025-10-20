package com.example.ProductService2025.Controllers;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){
        if(productId<1 || productId>20){
              return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.valueOf(200));
    }
}
