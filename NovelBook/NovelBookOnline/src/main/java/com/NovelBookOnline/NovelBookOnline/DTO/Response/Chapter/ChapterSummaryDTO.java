package com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter;

import java.time.LocalDateTime;

public record ChapterSummaryDTO (
        String chapterId,
        String chapterName,
        String totalReadChapter,
        LocalDateTime createdAt
)
{}