package com.NovelBookOnline.NovelBookOnline.DTO.Request.Author;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequest {

    @NotNull(message = "author name is required")
    String authorName;
    MultipartFile authorAvatar;
    String authorDescription;
    String userId;
}
