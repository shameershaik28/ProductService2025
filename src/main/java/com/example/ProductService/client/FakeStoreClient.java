package com.example.ProductService.client;

import com.example.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
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

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO input) {
        String createProductURL = "https://fakestoreapi.com/products";
        FakeStoreProductDTO response =  restTemplate.postForObject(createProductURL, input, FakeStoreProductDTO.class);
        return response;
    }

    public FakeStoreProductDTO updateProduct(int id, FakeStoreProductDTO input) {
        String updateProductURL = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO response =  putForObject(updateProductURL, input, FakeStoreProductDTO.class);
        return response;
    }
    private <T> T putForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return (T)restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
}
