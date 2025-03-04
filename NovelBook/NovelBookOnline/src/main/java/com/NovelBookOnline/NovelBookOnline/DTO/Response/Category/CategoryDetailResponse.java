package com.NovelBookOnline.NovelBookOnline.DTO.Response.Category;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;

import java.util.List;

public record CategoryDetailResponse (
        String id,
        String categoryName,
        String categoryInformation,
        int totalNovelsOfCategory,
        List<NovelSummaryResponse> novels
) {}
