package com.NovelBookOnline.NovelBookOnline.DTO.Request.Like;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeRequest {
    @NotNull(message = "chapter id is required")
    String chapterId;
}
