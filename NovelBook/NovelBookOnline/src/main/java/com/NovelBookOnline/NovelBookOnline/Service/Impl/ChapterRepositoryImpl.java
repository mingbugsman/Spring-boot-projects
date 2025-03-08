package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.ChapterJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterRepositoryImpl implements IChapterRepository {
    ChapterJpaRepository chapterJpaRepository;

    @Override
    public List<Chapter> getNewUpdateChapter() {
        return List.of();
    }

    @Override
    public List<Chapter> getBestChapterOfTheWeek() {
        return List.of();
    }

    @Override
    public List<Chapter> getChaptersByNovelId(String novelId) {
        return List.of();
    }

    public Chapter findChapterById(String id) {
        return chapterJpaRepository.getChapterById(id);
    }

    @Override
    public void delete(Chapter chapter) {

    }

    @Override
    public void add(Chapter chapter) {

    }
}
