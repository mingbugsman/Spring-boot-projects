package com.NovelBookOnline.NovelBookOnline.DTO.Request.Author;

import jakarta.validation.constraints.NotNull;

public class AuthorRequest {
    @NotNull(message = "id is required")
    String id;

    @NotNull(message = "author name is required")
    String authorName;
}
