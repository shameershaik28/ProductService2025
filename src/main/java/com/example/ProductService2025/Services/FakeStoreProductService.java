package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.dtos.FakeStoreProductDto;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import com.example.ProductService2025.projections.ProductInfo;
import com.example.ProductService2025.projections.ProductNameOnly;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service("fakestore")
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        /*
        Take the id from the input, and call this endpoint:a
        https://fakestoreapi.com/products/ + id
         */
        String url = "https://fakestoreapi.com/products/" + id;
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(url, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFoundException("Product with id: " + id +" was not found");
        }
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
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
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());
        return product;
    }
}
