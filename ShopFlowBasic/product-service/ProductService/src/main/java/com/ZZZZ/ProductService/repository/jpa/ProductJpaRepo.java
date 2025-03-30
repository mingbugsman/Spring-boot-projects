package com.ZZZZ.ProductService.repository.jpa;

import com.ZZZZ.ProductService.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductJpaRepo extends JpaRepository<Product, String> {
    Page<Product> findByDeleteAtIsNull(Pageable pageable);
    Product findByIdAndDeleteAtIsNull(String id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE products
            SET product_stock = product_stock - :stock
            WHERE id = :id
            """, nativeQuery = true)
    void decreaseStock(@Param("id") String productId, @Param("stock") int stock);
}
