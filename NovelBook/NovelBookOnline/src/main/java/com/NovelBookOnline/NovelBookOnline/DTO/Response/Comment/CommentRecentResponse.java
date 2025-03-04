package com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment;

import java.util.List;

public record CommentRecentResponse (
        String chapterId,
        List<CommentResponse> comments
){}
