package com.example.ProductService.service;

import com.example.ProductService.Repository.CategoryRepository;
import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.client.FakeStoreClient;
import com.example.ProductService.dto.FakeStoreProductDTO;
import com.example.ProductService.dto.ProductProjection;
import com.example.ProductService.dto.ProductReqDTO;
import com.example.ProductService.dto.SortDTO;
import com.example.ProductService.exception.CategoryNotFoundException;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.model.Category;
import com.example.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


//    public List<Product> getAllProductByCategoryId(int categoryId){
//        List<Product> products = categoryService.getAllProductsByCategory(categoryId);
//        return products;
//    }
public Product saveProduct(ProductReqDTO productReqDTO) {
    Category savedCategory = categoryRepository.findById(productReqDTO.getCategoryId()).orElseThrow(
            () -> new CategoryNotFoundException("Category does not exist")
    );

    Product product = new Product();
    product.setName(productReqDTO.getName());
    product.setDescription(productReqDTO.getDescription());
    product.setPrice(productReqDTO.getPrice());
    product.setQuantity(productReqDTO.getQuantity());
    product.setRating(productReqDTO.getRating());
    Product savedProduct = productRepository.save(product);

    savedCategory.getProducts().add(savedProduct);
    categoryRepository.save(savedCategory);

    return savedProduct;
}

    public boolean deleteProduct(int productId) {
         productRepository.deleteById(productId);
         return true;
    }

    public Product getProduct(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty())
        {
             throw new ProductNotFoundException("Product with id "+ productId + " not found");
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

    public Product updateProduct(Product newProduct, int productId){
        Product savedProduct = getProduct(productId);
        newProduct.setId(productId);
        Product updatedProduct = productRepository.save(newProduct);
        return updatedProduct;
    }
    public Page<Product> getAllProductsPaginated(int pageNumber, String filterAsc, String filterDesc){
        // Sort sort = Sort.by(parameter).ascen().and( Sort....).and(Sort....)
        Sort sort = Sort.by(filterAsc).ascending().and(Sort.by(filterDesc).descending());
        return productRepository.findAll(PageRequest.of(pageNumber, 3, sort));
    }

    public Page<Product> getAllProductsPaginated(int pageNumber, List<SortDTO> sortingDTOs) {
        Sort sort = Sort.unsorted();
        for (SortDTO dto : sortingDTOs) {
            if (dto.isFilterType()) {
                sort = sort.and(Sort.by(dto.getFilterName()).ascending());
            } else {
                sort = sort.and(Sort.by(dto.getFilterName()).descending());
            }
        }

        if (sort.isUnsorted()) {
            sort = Sort.by("price").ascending().and(Sort.by("rating").descending());
        }

        return productRepository.findAll(PageRequest.of(pageNumber, 3, sort));
    }


    public Page<Product> getAllProductsPaginated(int pageNumber, List<String> asc, List<String> desc) {
        Sort sort = Sort.unsorted();

        if (asc != null) {
            for (String field : asc) {
                sort = sort.and(Sort.by(field).ascending());
            }
        }

        if (desc != null) {
            for (String field : desc) {
                sort = sort.and(Sort.by(field).descending());
            }
        }

        // Default sort if both asc and desc are empty
        if (sort.isUnsorted()) {
            sort = Sort.by("price").ascending().and(Sort.by("rating").descending());
        }

        return productRepository.findAll(PageRequest.of(pageNumber, 3, sort));
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductProjection getProductProjection(String productName){
        return productRepository.findFirstByName(productName);
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
