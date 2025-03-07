package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment,String> {

    @Query(value = """
            SELECT co FROM comments
            WHERE co.id = :id AND co.deleted_at IS NULL
            """, nativeQuery = true)
    Comment getCommentById(@Param("id")String id);

    @Query("SELECT co FROM Chapter c " +
            "JOIN Comment co " +
            "WHERE c.id = :chapterId AND c.deletedAt IS NULL " +
            "ORDER BY c.createdAt DESC")
    Page<Comment> getAllCommentByChapterId(@Param("chapterId") String chapterId, Pageable pageable);



    @Query("SELECT c FROM Comment c " +
            "WHERE c.parent.id = :commentId AND c.deletedAt IS NULL " +
            "ORDER BY c.createdAt DESC")
    Page<Comment> getAllSubCommentByParentComment(@Param("commentId") String commentId, Pageable pageable);
}
