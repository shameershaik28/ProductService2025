package com.example.ProductService.service;

import com.example.ProductService.Repository.CategoryRepository;
import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.dto.CategoryRequestDTO;
import com.example.ProductService.exception.CategoryNotFoundException;
import com.example.ProductService.exception.DuplicateCategoryNameException;
import com.example.ProductService.model.Category;
import com.example.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;


    public Category createCategory(CategoryRequestDTO categoryRequestDTO){
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryRequestDTO.getCategoryName());
        if(categoryOptional.isPresent()){
            throw new DuplicateCategoryNameException("Duplicate category name " + categoryRequestDTO.getCategoryName());
        }
        Category category = new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getCategoryDescription());
        return categoryRepository.save(category);
    }

    public Category getCategory(int categoryId){
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found, categoryID : " + categoryId)
        );
        return savedCategory;
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Product> getAllProductsByCategory(int categoryId){
        Category savedCategory = getCategory(categoryId);
        List<Product> products = savedCategory.getProducts();
        return products;
    }

    public boolean deleteCategory(int categoryId){
        Category category = getCategory(categoryId);
        for(Product product : category.getProducts()){
            productService.deleteProduct(product.getId());
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }



}
