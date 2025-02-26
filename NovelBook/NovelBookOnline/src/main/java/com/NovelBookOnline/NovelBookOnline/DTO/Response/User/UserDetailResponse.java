package com.NovelBookOnline.NovelBookOnline.DTO.Response.User;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;

import java.util.List;

public record UserDetailResponse (
        String id,
        String username,
        String imageBase64,
        boolean gender,
        int totalReactions,
        int totalComments,
        List<NovelSummaryResponse> novelSummaryResponseList

) {}
