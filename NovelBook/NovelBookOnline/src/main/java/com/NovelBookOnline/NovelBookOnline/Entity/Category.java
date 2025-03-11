package com.NovelBookOnline.NovelBookOnline.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories", indexes = {
        @Index(name = "idx_category_id_deletedAt",columnList = "id, deleted_at"),
        @Index(name = "idx_deletedAt", columnList = "deleted_at"),
        @Index(name = "idx_category_name", columnList = "category_name")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "category_name", unique = true)
    String categoryName;

    @Column(name = "category_information", columnDefinition = "TEXT")
    String categoryInformation;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
            @JoinTable(name = "category_novel",
                        joinColumns = @JoinColumn(name = "category_id"),
                        inverseJoinColumns = @JoinColumn(name = "novel_id"))
    Set<Novel> novels;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", updatable = false, nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deleteAt;
}
