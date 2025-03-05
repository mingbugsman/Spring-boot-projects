package com.NovelBookOnline.NovelBookOnline.Entity;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String authorName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Novel> novels;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<LikeAuthor> likeAuthors;


}
