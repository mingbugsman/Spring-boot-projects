package com.NovelBookOnline.NovelBookOnline.DTO.Request.Like;


import com.NovelBookOnline.NovelBookOnline.Enum.LikeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeChapterRequest {

    @NotNull(message = "chapter id is required")
    private String chapterId;

    @NotNull(message = "like status is required")
    LikeStatus likeStatus;
}
