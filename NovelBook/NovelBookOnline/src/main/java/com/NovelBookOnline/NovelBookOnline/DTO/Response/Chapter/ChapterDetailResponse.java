package com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.ListCommentResponse;

public record ChapterDetailResponse (
    String id,
    int totalReadChapter,
    String chapterName,
    String chapterContent,
    int totalLikes,
    ListCommentResponse listCommentResponse
){}
