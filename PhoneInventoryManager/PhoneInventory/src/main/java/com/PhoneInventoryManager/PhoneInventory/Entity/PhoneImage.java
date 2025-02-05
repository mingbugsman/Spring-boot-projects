package com.PhoneInventoryManager.PhoneInventory.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "phone_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhoneImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "phone_id", nullable = false)
    Phone phone;

    @Column(name = "image_url",nullable = false)
    String imageUrl;

    @Builder.Default
    @Column(name = "is_primary")
    boolean isPrimary = false;
}
