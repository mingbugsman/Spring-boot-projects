package com.NovelBookOnline.NovelBookOnline.Entity;

import com.NovelBookOnline.NovelBookOnline.Enum.NovelStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "novels", indexes = {
        @Index(name = "idx_novel_name", columnList = "novel_name"),
        @Index(name = "idx_deleted_at", columnList = "deleted_at"),
        @Index(name = "idx_novel_id_deletedAt", columnList = "id, deleted_at")
})
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "novel_name", nullable = false, columnDefinition = "VARCHAR(255)")
    String novelName;

    @Lob
    @Column(name = "novel_cover_image", columnDefinition = "LONGBLOB")
    byte[] novelCoverImage;

    @Column(name = "novel_description", columnDefinition = "TEXT")
    String novelDescription;


    @Enumerated(EnumType.STRING)
    @Column(name = "novel_status", nullable = false)
    NovelStatus novelStatus = NovelStatus.UPDATING;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;


    @OneToMany(mappedBy = "novel", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    List<Chapter> chapters = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "category_novel",
            joinColumns = @JoinColumn(name = "novel_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();


    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
