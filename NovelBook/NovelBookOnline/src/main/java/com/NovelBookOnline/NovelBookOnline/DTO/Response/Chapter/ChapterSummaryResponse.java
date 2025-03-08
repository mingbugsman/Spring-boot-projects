package com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter;

public record ChapterSummaryResponse (
        String chapterId,
        String novelName,
        String novelCoverImageBase64,
        int chapterNumber
)
{}