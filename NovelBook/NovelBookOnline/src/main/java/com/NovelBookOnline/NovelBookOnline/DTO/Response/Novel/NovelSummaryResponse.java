package com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel;


import java.time.LocalDateTime;

public record NovelSummaryResponse(
        String id,
        String novelName,
        String novelCoverImage,
        LocalDateTime updateAt
) {
}
