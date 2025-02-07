package com.PhoneInventoryManager.PhoneInventory.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phones", indexes = {
        @Index(name = "idx_created_at", columnList = "created_at")
})
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

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PhoneImage> images = new ArrayList<>();

    @Builder.Default
    @Column(nullable = false)
    Integer soldQuantity = 0;

    // relationship
    @OneToOne(mappedBy = "phone", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Specification specification;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    // time stamp
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

}
