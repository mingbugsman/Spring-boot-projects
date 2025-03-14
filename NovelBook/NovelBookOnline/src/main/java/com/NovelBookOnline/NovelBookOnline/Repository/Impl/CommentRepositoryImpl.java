package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import com.NovelBookOnline.NovelBookOnline.Repository.ICommentRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements ICommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment findCommentById(String id) {
        return commentJpaRepository.getCommentById(id);
    }

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Page<Comment> getAllCommentsByChapterId(String chapterId, Pageable pageable) {
        return commentJpaRepository.getAllCommentByChapterId(chapterId, pageable);
    }


    @Override
    public Page<Comment> getAllSubCommentsByParentComment(String commentId, Pageable pageable) {
        return commentJpaRepository.getAllSubCommentByParentComment(commentId, pageable);
    }

    @Override
    public void delete(Comment comment) {
        comment.setDeletedAt(LocalDateTime.now());
        commentJpaRepository.save(comment);
    }




}
