package com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth;

import java.time.LocalDateTime;

public record RegisterResponse(
        String id,
        String username,
        String email,
        String accessToken,
        String refreshToken,
        LocalDateTime createdAt
) {
}
