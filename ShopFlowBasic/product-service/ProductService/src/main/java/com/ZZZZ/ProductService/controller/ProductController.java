package com.ZZZZ.ProductService.controller;


import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.response.product.ProductResponse;
import com.ZZZZ.ProductService.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductCreationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }


}
