package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.ChapterJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterRepositoryImpl implements IChapterRepository {
    ChapterJpaRepository chapterJpaRepository;

    @Override
    public Page<Chapter> getNewUpdateChapter(Pageable pageable) {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        List<String> ids = chapterJpaRepository.getAllIdsByDaily(oneDayAgo);
        return chapterJpaRepository.getChaptersByIds(ids, pageable);
    }

    @Override
    public List<Chapter> getTop25HottestChapters() {
        LocalDateTime twelveDaysAgo = LocalDateTime.now().minusDays(11);
        List<String> ids =  chapterJpaRepository.findTop25HottestChapterIds(twelveDaysAgo);
        return chapterJpaRepository.getChaptersByIds(ids);
    }

    @Override
    public Page<Chapter>getChaptersByNovelId(String novelId, Pageable pageable) {
        var ids = chapterJpaRepository.getAllIdsByNovelId(novelId);
        return chapterJpaRepository.getChaptersByIds(ids, pageable);
    }

    public Chapter findChapterById(String id) {
        return chapterJpaRepository.getChapterById(id).orElseThrow(() -> new ApplicationException(ErrorCode.CATEGORY_EXISTED));
    }

    @Override
    public void delete(Chapter chapter) {
        chapter.setDeletedAt(LocalDateTime.now());
        chapterJpaRepository.save(chapter);
    }

    @Override
    public void save(Chapter chapter) {
        chapterJpaRepository.save(chapter);
    }

    @Override
    public boolean existsByChapterNameAndChapterNumber(String chapterName, int chapterNumber) {
        return chapterJpaRepository.existsByChapterNameAndChapterNumber(chapterName, chapterNumber);
    }
}
