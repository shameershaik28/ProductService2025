package com.example.ProductService.controller;

import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.dto.ProductProjection;
import com.example.ProductService.dto.ProductReqDTO;
import com.example.ProductService.dto.ProductResponseDTO;
import com.example.ProductService.model.Product;
import com.example.ProductService.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RestController
//@RequestMapping("/v1/product")
public class ProductController {

   @Autowired
  private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductReqDTO productReqDTO){
        Product savedProduct = productService.saveProduct(productReqDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct()
    {
      List<Product> products = productService.getAllProducts();
      return ResponseEntity.ok(products);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id)
    {
        Product savedProduct = productService.getProduct(id);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products/desc/{description}") // localhost:8080/product/desc/something
    public ResponseEntity<List<Product>> getProductByDescription(@PathVariable("description") String description){
        List<Product> matchedProducts = productService.getProductByDescription(description);
        return ResponseEntity.ok(matchedProducts);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable("id") int id)
    {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/projection/{name}") // localhost:8080/product/desc/something
    public ResponseEntity<ProductProjection> getProductProjectionByName(@PathVariable("name") String name){
        ProductProjection projection = productService.getProductProjection(name);
        return ResponseEntity.ok(projection);
    }

    @GetMapping("/products/fake")
    public FakeStoreProductDTO[] getAllProductsFromFakeStore()
    {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("/products/{id}/fake")
    public ResponseEntity<FakeStoreProductDTO> getProductByIdFromFakeStore(@PathVariable("id") int id)
    {
        if(id<=0)
        {
            throw  new IllegalArgumentException("Product does not exist");
            //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        FakeStoreProductDTO response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products/fake")
    public FakeStoreProductDTO createProductFromFakeStore(@RequestBody FakeStoreProductDTO product)
    {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}/fake")
    public FakeStoreProductDTO updateProductFromFakeStore(@PathVariable("id") int id, @RequestBody FakeStoreProductDTO product)
    {
        return productService.updateProduct(id, product);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
