package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.DailyRead;

import java.time.LocalDate;


public interface IDailyReadingRepository {
    void save(DailyRead dailyRead);
    DailyRead findByChapterIdAndCreatedAt(String chapterId, LocalDate createdAt);
}
