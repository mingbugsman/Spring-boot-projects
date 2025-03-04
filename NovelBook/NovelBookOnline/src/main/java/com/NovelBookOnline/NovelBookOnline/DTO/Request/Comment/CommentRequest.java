package com.NovelBookOnline.NovelBookOnline.DTO.Request.Comment;

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
public class CommentRequest {
    @NotNull(message = "content is required")
    String content;

    MultipartFile fileImage;

    @NotNull(message = "chapter id is required")
    String chapterId;

    String parentCommentId;
}

