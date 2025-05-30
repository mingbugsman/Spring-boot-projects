package com.ZZZZ.ProductService.repository;

import com.ZZZZ.ProductService.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepo {
    Product getProduct(String id);
    Page<Product> getAll(Pageable pageable);

    Product save(Product product);
    void softDelete(Product product);
    void absoluteDelete(Product product);

    void decreaseStock(String id, int stock);
}
