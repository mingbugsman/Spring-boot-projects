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
public class LikeCommentRequest {
    @NotNull(message = "comment id is required")
    private String commentId;

    @NotNull(message = "like status is required")
    LikeStatus likeStatus;
}
