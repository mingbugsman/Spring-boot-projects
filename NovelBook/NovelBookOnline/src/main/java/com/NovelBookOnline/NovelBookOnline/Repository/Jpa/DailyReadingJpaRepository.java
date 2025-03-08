package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.DailyRead;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface DailyReadingJpaRepository extends JpaRepository<DailyRead, String> {

    DailyRead findByChapterIdAndCreatedAt(String chapterId, LocalDate createdAt);
}
