package com.NovelBookOnline.NovelBookOnline.Entity;


import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapters", indexes = {
        @Index(name = "idx_deletedAt", columnList = "deleted_at"),
        @Index(name = "idx_createdAt", columnList = "created_at")
})
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    @Column(name = "chapter_name")
    String chapterName;

    @Column(name = "chapter_number", nullable = false)
    int chapterNumber;

    @Column(name = "chapter_content", nullable = false, columnDefinition = "LONGTEXT")
    String ChapterContent;

    @OneToMany(mappedBy = "chapter", cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REMOVE})
    List<LikeChapter> likes = new ArrayList<>();
    @OneToMany(mappedBy = "chapter", cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REMOVE})
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "chapter", cascade = {CascadeType.ALL})
    List<DailyRead> dailyReads = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "novel_id", nullable = false)
    Novel novel;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
