package com.NovelBookOnline.NovelBookOnline.DTO.Request.Author;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class AuthorRequest {
    @NotNull(message = "id is required")
    String id;

    @NotNull(message = "author name is required")
    String authorName;

    MultipartFile authorAvatar;
    String authorDescription;
}
