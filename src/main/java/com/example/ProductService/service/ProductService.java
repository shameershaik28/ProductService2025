package com.example.ProductService.service;

import com.example.ProductService.client.FakeStoreClient;
import com.example.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductDTO[] getAllProductsFromFakeStore() {
        return fakeStoreClient.getAllProducts();
    }

    public FakeStoreProductDTO getProductById(int id) {
        return fakeStoreClient.getProductById(id);
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO product) {
        return fakeStoreClient.createProduct(product);
    }

    public FakeStoreProductDTO updateProduct(int id, FakeStoreProductDTO product) {
        return fakeStoreClient.updateProduct(id, product);
    }
}
