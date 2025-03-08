package com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment;

import java.util.List;

public record ListCommentResponse (
        String chapterId,
        List<CommentResponse> comments
){}
