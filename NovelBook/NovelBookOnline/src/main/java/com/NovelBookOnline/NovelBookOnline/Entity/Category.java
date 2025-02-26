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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String categoryName;

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
}
