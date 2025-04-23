package com.ZZZZ.ProductService.controller;


import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.request.ProductUpdateRequest;
import com.ZZZZ.ProductService.DTO.response.ApiResponse;
import com.ZZZZ.ProductService.DTO.response.product.ProductResponse;
import com.ZZZZ.ProductService.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(page, size, sortBy));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getAll(
            @PathVariable String productId
    ) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(productId))
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProductId(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<Void>builder().message("done deleted product").build();
    }

    @PatchMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(
            @PathVariable String productId,
            @RequestBody ProductUpdateRequest request

    ) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId, request))
                .build();
    }

}
