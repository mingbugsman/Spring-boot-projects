package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ICommentRepository {
    void save(Comment comment);
    Page<Comment> getAllCommentsByChapterId(String chapterId, Pageable pageable);
  //  Page<Comment> getAllCommentsByTotalLike(String chapterId, Pageable page);
    List<Comment> getAllSubCommentsByParentComment(String commentId);
    void delete(Comment comment);
    // like comment
    void likeComment(Comment comment);
    void unLikeComment(Comment comment);
}
