package com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter;

import java.time.LocalDateTime;

public record ChapterSummaryResponse (
        String chapterId,
        String novelName,
        String novelCoverImageBase64,
        int chapterNumber,
        String chapterName,
        String totalReadChapter,
        LocalDateTime createdAt
)
{}