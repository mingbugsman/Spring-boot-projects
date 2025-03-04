package com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment;

public record CommentResponse(
        String username,
        String content,
        String fileBase64,
        int totalComments
) {
}
