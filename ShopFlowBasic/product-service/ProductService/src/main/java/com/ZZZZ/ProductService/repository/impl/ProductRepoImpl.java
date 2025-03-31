package com.ZZZZ.ProductService.repository.impl;

import com.ZZZZ.ProductService.entity.Product;
import com.ZZZZ.ProductService.repository.ProductRepo;
import com.ZZZZ.ProductService.repository.jpa.ProductJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
@RequiredArgsConstructor
public class ProductRepoImpl implements ProductRepo {
    private final ProductJpaRepo productJpaRepo;

    @Override
    public Product getProduct(String id) {
        return productJpaRepo.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productJpaRepo.findByDeleteAtIsNull(pageable);
    }

    @Override
    public Product save(Product product) {
        product = productJpaRepo.save(product);
        return product;
    }

    @Override
    public void softDelete(Product product) {
        product.setDeleteAt(LocalDateTime.now());
        productJpaRepo.save(product);
    }

    @Override
    public void absoluteDelete(Product product) {
        productJpaRepo.delete(product);
    }

    @Override
    public void decreaseStock(String id, int stock) {
        productJpaRepo.decreaseStock(id, stock);
    }
}
