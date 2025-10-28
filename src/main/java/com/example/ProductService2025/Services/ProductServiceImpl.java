package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("dbImpl")
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public Product createProduct(String name, String category, String description) {

        Product p = productRepository.findFirstByNameAndCategory(name, category);
        if(p!=null){
            return p;
        }
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product= productRepository.save(product);
        System.out.println(product.getId());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        existing.setName(name);
        existing.setCategory(category);
        existing.setDescription(description);

        Product updated = productRepository.save(existing);
        return updated;
    }

    @Override
    public void deleteProduct(UUID id) throws ProductNotFoundException {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        productRepository.delete(existing);
        // or: productRepository.deleteById(id); // but we used findById first to throw same exception if missing
    }
}
