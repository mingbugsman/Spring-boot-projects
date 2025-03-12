package com.NovelBookOnline.NovelBookOnline.Entity;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DynamicInsert
@Table(name = "authors", indexes = {
        @Index(columnList = "deleted_at")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "author_name", nullable = false)
    String authorName;
    @Column(name = "author_description")
    String authorDescription;


    @Lob
    @Column(name = "author_avatar", columnDefinition = "LONGBLOB")
    byte[] authorAvatar;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Novel> novels = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<LikeAuthor> likeAuthors = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
