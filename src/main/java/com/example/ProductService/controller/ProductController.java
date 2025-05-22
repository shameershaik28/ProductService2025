package com.example.ProductService.controller;

import com.example.ProductService.dto.*;
import com.example.ProductService.model.Product;
import com.example.ProductService.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/all/product/{pageNumber}/{ascFilter}/{descFilter}")
    public ResponseEntity<Page<Product>> getAllProducts(@PathVariable("pageNumber") int pageNumber,
                                                        @PathVariable("ascFilter") String ascFilter,
                                                        @PathVariable("descFilter") String descFilter ){
        Page<Product> products = productService.getAllProductsPaginated(pageNumber, ascFilter, descFilter);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/all/products/{pageNumber}")
    public ResponseEntity<Page<Product>> getAllProducts(
            @PathVariable int pageNumber,
            @RequestBody List<SortDTO> sortDTOs) {
        return ResponseEntity.ok(productService.getAllProductsPaginated(pageNumber, sortDTOs));
    }

    // Controller method to get paginated and sorted products
// Supports sorting in ascending or descending order via query parameters
    @GetMapping("/all/products/{pageNumber}")
    public ResponseEntity<Page<Product>> getAllProducts(
            @PathVariable("pageNumber") int pageNumber,
            @RequestParam(required = false) List<String> asc,
            @RequestParam(required = false) List<String> desc) {

        Page<Product> products = productService.getAllProductsPaginated(pageNumber, asc, desc);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/all/product")
    public ResponseEntity<List<Product>> getAllProducts(){
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
