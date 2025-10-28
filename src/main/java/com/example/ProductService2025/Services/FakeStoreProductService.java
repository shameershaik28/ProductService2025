package com.example.ProductService2025.Services;

import com.example.ProductService2025.Models.Product;
import com.example.ProductService2025.dtos.FakeStoreProductDto;
import com.example.ProductService2025.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakestore")
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
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
    public Product createProduct(String name, String category, String description) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, String name, String category, String description) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());
        return product;
    }
}
