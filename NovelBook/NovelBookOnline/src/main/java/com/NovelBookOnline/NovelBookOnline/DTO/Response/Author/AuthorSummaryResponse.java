package com.NovelBookOnline.NovelBookOnline.DTO.Response.Author;

public record AuthorSummaryResponse(
        String id,
        String authorName,
        String authorAvatarBase64,
        String authorDescription
) {
}
