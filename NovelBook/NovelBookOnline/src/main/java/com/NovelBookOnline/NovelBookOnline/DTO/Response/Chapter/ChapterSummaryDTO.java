package com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter;

import java.time.LocalDateTime;

public record ChapterSummaryDTO (
        String chapterId,
        int chapterNumber,
        String chapterName,
        String totalReadChapter,
        LocalDateTime createdAt
)
{}