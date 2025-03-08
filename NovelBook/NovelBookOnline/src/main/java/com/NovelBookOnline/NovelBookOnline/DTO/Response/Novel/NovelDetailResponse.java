package com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;

import java.util.List;

public record NovelDetailResponse (
        String id,
        String novelCoverImage, // Base64
        String novelName,
        String novelDescription,
        String authorName,
        List<String> categoryNames,
        int totalReads,
        int totalLikes,
        List<ChapterSummaryResponse> chapterSummaryDTOS,
        List<CommentResponse> comment
) {
}
