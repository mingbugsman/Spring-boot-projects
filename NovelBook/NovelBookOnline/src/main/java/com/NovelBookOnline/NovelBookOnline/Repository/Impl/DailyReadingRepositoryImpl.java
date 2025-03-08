package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.DailyRead;
import com.NovelBookOnline.NovelBookOnline.Repository.IDailyReadingRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.DailyReadingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;



@Repository
@RequiredArgsConstructor
public class DailyReadingRepositoryImpl implements IDailyReadingRepository {

    private final DailyReadingJpaRepository dailyReadingJpaRepository;

    @Override
    public void save(DailyRead dailyRead) {
        dailyReadingJpaRepository.save(dailyRead);
    }

    @Override
    public DailyRead findByChapterIdAndCreatedAt(String chapterId, LocalDate createdAt) {
        return dailyReadingJpaRepository.findByChapterIdAndCreatedAt(chapterId, createdAt);
    }
}
