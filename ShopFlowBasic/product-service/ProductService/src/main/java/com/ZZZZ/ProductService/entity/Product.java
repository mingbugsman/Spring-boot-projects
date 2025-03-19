package com.ZZZZ.ProductService.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, name = "product_name")
    String name;


    @Column(name = "product_description")
    String description;

    @Column(nullable = false, name = "product_price")
    double price;

    @Column(nullable = false, name = "product_stock")
    int stock;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = true)
    LocalDateTime updateAt;

    @Column(name = "deleted_at")
    LocalDateTime deleteAt;
}
