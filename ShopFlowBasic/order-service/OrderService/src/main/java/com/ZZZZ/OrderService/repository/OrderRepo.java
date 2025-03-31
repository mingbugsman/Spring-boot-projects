package com.ZZZZ.OrderService.repository;

import com.ZZZZ.OrderService.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    Order findByIdAndDeletedAtIsNull(String id);
    Page<Order> findByUserIdAndDeletedAtIsNull(String userId, Pageable pageable);
    Page<Order> findByDeletedAtIsNull(Pageable pageable);
    Page<Order> findByProductIdAndDeletedAtIsNull(String productId, Pageable pageable);
    List<Order> findByProductIdAndDeletedAtIsNull(String productId);
}
