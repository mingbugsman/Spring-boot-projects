package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.DailyRequest;

public interface IDailyReadingService {
    void recordRead(DailyRequest request);
}
