package com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter;


import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyRequest {
    @NotNull(message = "id for every reading is required")
    private String id; // uuid

    @NotNull(message = "chapter id is required")
    private String chapterId;
}
