package com.NovelBookOnline.NovelBookOnline.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterRequest {

    String chapterName;

    @NotNull(message = "chapter number is required")
    int chapterNumber;

    @NotNull(message = "chapter content is required")
    String chapterContent;
}
