package com.PhoneInventoryManager.PhoneInventory.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "phone_images")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class PhoneImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Lob
    @Column(name = "data_image",nullable = false, columnDefinition = "LONGBLOB")
    byte[] dataImage;

    @Builder.Default
    @Column(name = "is_primary")
    boolean isPrimary = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_id", nullable = false)
    Phone phone;
}
