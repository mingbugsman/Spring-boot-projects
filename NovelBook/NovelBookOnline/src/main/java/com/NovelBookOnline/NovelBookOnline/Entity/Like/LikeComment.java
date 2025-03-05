package com.NovelBookOnline.NovelBookOnline.Entity.Like;


import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "like_comment")
public class LikeComment extends Like{

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;
}
