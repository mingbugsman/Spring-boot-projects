package com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel;



import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// for update
public class NovelUpdateRequest {

    String novelName;
    MultipartFile novelCoverImage = null;
    String novelDescription;
    String authorId;
    List<String> categoryIds;
}
