package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ICommentRepository {
    Comment findCommentById(String id);
    void save(Comment comment);
    Page<Comment> getAllCommentsByChapterId(String chapterId, Pageable pageable);
  //  Page<Comment> getAllCommentsByTotalLike(String chapterId, Pageable page);
    Page<Comment> getAllSubCommentsByParentComment(String commentId, Pageable pageable);
    void delete(Comment comment);

}
