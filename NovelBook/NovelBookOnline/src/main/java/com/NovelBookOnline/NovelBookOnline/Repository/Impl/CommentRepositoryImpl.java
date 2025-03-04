package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import com.NovelBookOnline.NovelBookOnline.Repository.ICommentRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements ICommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Page<Comment> getAllCommentsByChapterId(String chapterId, Pageable pageable) {
        return commentJpaRepository.getAllCommentByChapterId(chapterId, pageable);
    }

    @Override
    public Page<Comment> getAllCommentsByTotalLike(String chapterId, Pageable pageable) {
        return commentJpaRepository.getAllCommentsByTotalLike(chapterId, pageable);
    }

    @Override
    public List<Comment> getAllSubCommentsByParentComment(String commentId) {
        return commentJpaRepository.getAllSubCommentByParentComment(commentId);
    }

    @Override
    public void delete(Comment comment) {
        comment.setDeletedAt(LocalDateTime.now());
        commentJpaRepository.save(comment);
    }

    @Override
    public void likeComment(Comment comment) {
        int minus = comment.getTotalLikes();
        comment.setTotalLikes(++minus);
        commentJpaRepository.save(comment);
    }

    @Override
    public void unLikeComment(Comment comment) {
        int minus = comment.getTotalLikes();
        comment.setTotalLikes(--minus);
        commentJpaRepository.save(comment);
    }
}
