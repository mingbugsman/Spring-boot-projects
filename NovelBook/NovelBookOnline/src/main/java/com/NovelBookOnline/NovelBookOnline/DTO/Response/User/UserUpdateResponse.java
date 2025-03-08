package com.NovelBookOnline.NovelBookOnline.DTO.Response.User;

import java.time.LocalDateTime;

public record UserUpdateResponse (
        String id,
        String username,
        String email,
        boolean gender,
        String imageBase64,
        LocalDateTime updateAt
) {}
