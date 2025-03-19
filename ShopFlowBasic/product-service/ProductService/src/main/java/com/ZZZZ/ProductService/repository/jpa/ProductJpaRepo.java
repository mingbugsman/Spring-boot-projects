package com.ZZZZ.ProductService.repository.jpa;

import com.ZZZZ.ProductService.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductJpaRepo extends JpaRepository<Product, String> {
    Page<Product> findByDeleteAtIsNull(Pageable pageable);
    Product findByIdAndDeleteAtIsNull(String id);

}
