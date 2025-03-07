package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Comment.CommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Comment;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import com.NovelBookOnline.NovelBookOnline.Mapper.CommentMapper;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.ICommentRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Security.SecurityUtils;
import com.NovelBookOnline.NovelBookOnline.Service.ICommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService implements ICommentService {
    IUserRepository userRepository;
    IChapterRepository chapterRepository;
    ICommentRepository commentRepository;
    CommentMapper commentMapper;
    CustomerMappingHelper customerMappingHelper;

    public String getUserId() {
        String userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        return userId;
    }

    @Override
    public CommentResponse addComment(CommentRequest request) {
        User user = userRepository.findUserById(getUserId());
        Chapter chapter = chapterRepository.findChapterById(request.getChapterId());
        Comment comment = commentMapper.toEntity(request);
        comment.setUser(user);
        comment.setChapter(chapter);
        if (request.getParentCommentId() != null) {
            Comment parent = commentRepository.findCommentById(request.getParentCommentId());
            comment.setParent(parent);
        }
        userRepository.save(user);
        return customerMappingHelper.toCommentResponse(comment);
    }

    @Override
    public Page<CommentResponse> getAllCommentsByChapterId(String chapterId, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return commentRepository.getAllCommentsByChapterId(chapterId, pageable).map(customerMappingHelper::toCommentResponse);
    }

    @Override
    public Page<CommentResponse> getAllSubCommentsByParentComment(String commentId, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return commentRepository.getAllSubCommentsByParentComment(commentId, pageable).map(customerMappingHelper::toCommentResponse);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        if (comment.getUser().getId().equals(getUserId())) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        commentRepository.delete(comment);
    }
}
