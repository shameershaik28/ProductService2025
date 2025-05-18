package com.example.ProductService.controller;

import com.example.ProductService.dto.CategoryRequestDTO;
import com.example.ProductService.dto.CategoryResponseDTO;
import com.example.ProductService.dto.ProductResponseDTO;
import com.example.ProductService.model.Category;
import com.example.ProductService.model.Product;
import com.example.ProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Category savedCategory = categoryService.createCategory(categoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO =
                new CategoryResponseDTO(savedCategory.getName(), savedCategory.getDescription());
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") int id){
        Category savedCategory = categoryService.getCategory(id);
        CategoryResponseDTO categoryResponseDTO =
                new CategoryResponseDTO(savedCategory.getName(), savedCategory.getDescription());
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryResponseDTO responseDTO = new CategoryResponseDTO(
                    category.getName(), category.getDescription()
            );
            categoryResponseDTOS.add(responseDTO);
        }
        return ResponseEntity.ok(categoryResponseDTOS);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
    @GetMapping("/products/category/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategory(@PathVariable("id") int categoryId) {
        List<Product> savedProducts = categoryService.getAllProductsByCategory(categoryId);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : savedProducts) {
            ProductResponseDTO responseDTO = new ProductResponseDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getRating()
            );
            productResponseDTOS.add(responseDTO);
        }
        return ResponseEntity.ok(productResponseDTOS);
    }

}
