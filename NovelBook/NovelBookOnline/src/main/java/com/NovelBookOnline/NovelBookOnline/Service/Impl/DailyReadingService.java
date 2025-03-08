package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.DailyRequest;
import com.NovelBookOnline.NovelBookOnline.Entity.DailyRead;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IDailyReadingRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IDailyReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DailyReadingService implements IDailyReadingService {
    private final IChapterRepository chapterRepository;
    private final IDailyReadingRepository dailyReadingRepository;

    @Override
    public void recordRead(DailyRequest dailyRequest) {
        LocalDate now = LocalDate.now();
        Chapter chapter = chapterRepository.findChapterById(dailyRequest.getChapterId());
        DailyRead dailyRead = dailyReadingRepository.findByChapterIdAndCreatedAt(dailyRequest.getChapterId(), now);
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
