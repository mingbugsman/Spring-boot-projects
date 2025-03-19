package com.ZZZZ.ProductService.mapper;


import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.request.ProductUpdateRequest;
import com.ZZZZ.ProductService.DTO.response.ProductResponse;
import com.ZZZZ.ProductService.entity.Product;
import org.springframework.stereotype.Component;

@Component
public final class ProductMapper {

    public Product toProduct(ProductCreationRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription() != null ? request.getDescription() : "");
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        return product;
    }
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
    public void updateProduct(Product product, ProductUpdateRequest request) {
        product.setName(request.getName() != null ? request.getName() : product.getName());
        product.setDescription(request.getDescription() != null ? request.getDescription() : product.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
    }
}
