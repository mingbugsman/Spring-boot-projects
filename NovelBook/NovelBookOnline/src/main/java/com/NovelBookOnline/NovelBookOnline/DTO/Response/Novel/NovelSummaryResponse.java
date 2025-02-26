package com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryDTO;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Comment;

import java.util.List;

public record NovelSummaryResponse(
        String id,
        String novelName,
        String novelDescription,
        String author_id,
        List<String> categoryIds,
        int totalReads,
        int totalLikes,
        List<ChapterSummaryDTO> chapterSummaryDTOS,
        List<CommentResponse> comments
) {
}
