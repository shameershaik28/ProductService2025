package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;
import com.example.ProductService2025.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service("dbImpl")
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    public Product getProductById(UUID id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        else{
            throw new ProductNotFoundException("Product with id:" + id + " is not found");
        }
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Optional<ProductInfo> getProductInfo(UUID id) {
        return productRepository.getProductInfo(id);
    }


    public Optional<ProductNameOnly> getProductName(UUID id) {
        return productRepository.findProductById(id, ProductNameOnly.class);
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNum) {
        pageSize = Math.min(pageSize,100);
        return productRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by("name").descending().and(
                Sort.by("category")
        )));
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
