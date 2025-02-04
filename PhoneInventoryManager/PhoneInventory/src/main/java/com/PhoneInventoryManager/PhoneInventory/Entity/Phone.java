package com.PhoneInventoryManager.PhoneInventory.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Phones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {

    // properties entity
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String model;

    @Column(nullable = false)
    String brand;

    @Column(nullable = false)
    BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    Integer stockQuantity;

    @Column(name = "image_Path")
    String imagePath;


    // relationship
    @OneToOne(mappedBy = "phone", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Specification specification;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    // time stamp
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

}
