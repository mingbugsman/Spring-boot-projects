package com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

// for update and creation
public class NovelRequest {
    @NotNull(message = "Novel name is required")
    String novelName;

    MultipartFile novelCoverImage;

    String novelDescription;

    @NotNull(message = "author id is required")
    String authorId;

    @NotNull(message = "category ids is required")
    List<String> categoryIds;
}
