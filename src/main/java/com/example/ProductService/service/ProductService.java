package com.example.ProductService.service;

import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.client.FakeStoreClient;
import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Boolean deleteProduct(int productId) {
         productRepository.deleteById(productId);
         return true;
    }

    public Product getProduct(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty())
        {
             throw new ProductNotFoundException("Product with id"+ productId + "not found");
        }
        else
        {
            return productOptional.get();
        }

    }


    public List<Product> getProductByDescription(String description){
//        List<Product> products = productRepository.findAll();
//        List<Product> matchedProducts = new ArrayList<>();
//        for(Product product : products){
//            if(product.getDescription().equals(description)){
//                matchedProducts.add(product);
//            }
//        }
//        return matchedProducts;
        List<Product> matchedProducts = productRepository.findAllByDescriptionIgnoreCase(description);
        return matchedProducts;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


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
