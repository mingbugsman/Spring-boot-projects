package com.NovelBookOnline.NovelBookOnline.Entity.Like;


import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "like_author")
public class LikeAuthor extends Like{

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
