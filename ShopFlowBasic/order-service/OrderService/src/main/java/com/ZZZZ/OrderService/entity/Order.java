package com.ZZZZ.OrderService.entity;


import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.Enum.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "product_id", nullable = false)
    String productId;

    @Column(name = "user_id", nullable = false)
    String userId;

    @Column(name = "quantity")
    int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    OrderStatus orderStatus = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    PaymentMethod paymentMethod;

    @Column(name = "location_ship", nullable = false)
    String locationShipping;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = true)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", nullable = false)
    LocalDateTime updatedAt = null;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt = null;



}
