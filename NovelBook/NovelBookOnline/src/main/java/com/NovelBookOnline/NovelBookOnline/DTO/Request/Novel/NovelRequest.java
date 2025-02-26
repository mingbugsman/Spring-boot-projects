package com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

// for update and creation
public class NovelRequest {
    @NotNull(message = "Novel name is required")
    String novelName;

    byte[] novelCoverImage;

    String novelDescription;

    @NotNull(message = "author id is required")
    String author_id;

    @NotNull(message = "category ids is required")
    List<String> categoryIds;
}
