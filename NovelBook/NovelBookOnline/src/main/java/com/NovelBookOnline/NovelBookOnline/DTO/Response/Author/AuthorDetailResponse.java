package com.NovelBookOnline.NovelBookOnline.DTO.Response.Author;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;

import java.util.List;

public record AuthorDetailResponse (
        String id,
        String authorName,
        List<NovelSummaryResponse> novels
) {}