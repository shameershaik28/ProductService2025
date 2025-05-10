package com.example.ProductService.client;

import com.example.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplate restTemplate;


    public FakeStoreProductDTO[] getAllProducts() {
        String getAllProductURL = "https://fakestoreapi.com/products";
        FakeStoreProductDTO[] response =  restTemplate.getForObject(getAllProductURL, FakeStoreProductDTO[].class);
        return response;

    }

    public FakeStoreProductDTO getProductById(int id) {
        String getProductByIdURL = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO response =  restTemplate.getForObject(getProductByIdURL, FakeStoreProductDTO.class);
        return response;
    }
}
