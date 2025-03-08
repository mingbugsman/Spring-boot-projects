package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.DailyRead;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IDailyReadingRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IDailyReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyReadingService implements IDailyReadingService {
    private final IChapterRepository chapterRepository;
    private final IDailyReadingRepository dailyReadingRepository;

    @Override
    public void recordRead(String chapterId) {
        LocalDate now = LocalDate.now();
        Chapter chapter = chapterRepository.findChapterById(chapterId);
        DailyRead dailyRead = dailyReadingRepository.findByChapterIdAndCreatedAt(chapterId, now);
        if (dailyRead == null) {
            dailyRead = DailyRead.builder()
                    .chapter(chapter)
                    .createdAt(now)
                    .readCount(1)
                    .build();
        } else {
            dailyRead.setReadCount(dailyRead.getReadCount() + 1);
        }
        dailyReadingRepository.save(dailyRead);
    }
}
