package com.ZZZZ.ProductService.service;


import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.request.ProductUpdateRequest;
import com.ZZZZ.ProductService.DTO.response.ProductResponse;
import org.springframework.data.domain.Page;



public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    ProductResponse getProduct(String id);
    Page<ProductResponse> getAllProducts(int page, int size, String sortBy);
    ProductResponse updateProduct(String id, ProductUpdateRequest request);
    void deleteProduct(String id);

}
