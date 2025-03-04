package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment,String> {



    @Query("SELECT c.novels FROM Chapter c" +
            "WHERE c.id = :chapterId AND c.deleted IS NULL" +
            "ORDER BY c.createdAt DESC")
    Page<Comment> getAllCommentByChapterId(@Param("chapterId") String chapterId, Pageable pageable);


    @Query("SELECT c.novels FROM Chapter c" +
            "WHERE c.id = :chapterId AND c.deleted IS NULL" +
            "ORDER BY c.totalLikes DESC")
    Page<Comment> getAllCommentsByTotalLike(@Param("chapterId") String chapterId, Pageable pageable);

    @Query("SELECT c FROM Comment c" +
            "WHERE c.parent.id = :commentId AND c.deleted IS NULL" +
            "ORDER BY c.createdAt DESC")
    List<Comment> getAllSubCommentByParentComment(@Param("commentId") String commentId);
}
