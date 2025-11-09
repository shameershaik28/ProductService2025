package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.dtos.FakeStoreProductDto;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service("fakestore")
public class FakeStoreProductService implements ProductService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    // Implement the Long version for fakestore
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        Product product = (Product) this.redisTemplate.opsForHash().get("PRODUCTS", "product_" +  id);
        if(product != null){
            return product;
        }
        String url = "https://fakestoreapi.com/products/" + id;

        FakeStoreProductDto dto = this.restTemplate.getForObject(url, FakeStoreProductDto.class);

        if (dto == null) {
            throw new ProductNotFoundException("Product not found for ID: " + id);
        }
        product =  convertFakeStoreProductToProduct(dto);
        this.redisTemplate.opsForHash().put("PRODUCTS", "product_" +  id, product);
        return product;
    }

    // Optional: throw if someone accidentally calls UUID version on this service
    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        throw new ProductNotFoundException("FakeStore service expects numeric IDs. Use /fakestore/{id} with numeric id.");
    }

    @Override
    public Optional<ProductInfo> getProductInfo(UUID id) {
        return Optional.empty();
    }

    @Override
    public Product createProduct(String name, String category, String description) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNum) {
        return null;
    }


    @Override
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(UUID id) throws ProductNotFoundException {

    }

    @Override
    public Optional<ProductNameOnly> getProductName(UUID id) {
        return Optional.empty();
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(UUID.randomUUID()); // <-- add this line
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());
        return product;
    }
}
