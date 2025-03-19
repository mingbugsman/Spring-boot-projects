package com.ZZZZ.ProductService;

import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.response.ProductResponse;
import com.ZZZZ.ProductService.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void createProduct() {
        ProductCreationRequest request = new ProductCreationRequest("Fumo Cirno", null, 5000000, 20);
        ProductResponse response = productService.createProduct(request);
        System.out.println(response);
    }
}
