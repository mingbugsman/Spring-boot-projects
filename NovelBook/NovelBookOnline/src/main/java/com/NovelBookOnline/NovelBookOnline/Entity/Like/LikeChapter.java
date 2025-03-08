package com.NovelBookOnline.NovelBookOnline.Entity.Like;


import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "like_chapter", indexes = {
        @Index(name = "idx_chapter_likes", columnList = "chapter_id")
})
public class LikeChapter extends Like{

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

}
