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
public class LikeAuthorRequest {

    @NotNull(message = "author id is required")
    private String authorId;

    @NotNull(message = "like status is required")
    LikeStatus likeStatus;
}
