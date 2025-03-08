package com.NovelBookOnline.NovelBookOnline.DTO.Response.User;

import java.time.LocalDateTime;

public record UserSummaryResponse(
        String id,
        String username,
        String imageBase64,
        LocalDateTime createdAt
) {
}
