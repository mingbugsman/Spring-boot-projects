package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Comment.CommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import org.springframework.data.domain.Page;

public interface ICommentService {
    CommentResponse addComment(CommentRequest request);
    Page<CommentResponse> getAllCommentsByChapterId(String chapterId, int pageNumber, int size);
    Page<CommentResponse> getAllSubCommentsByParentComment(String commentId, int pageNumber, int size);
    void deleteComment(String commentId);
}
