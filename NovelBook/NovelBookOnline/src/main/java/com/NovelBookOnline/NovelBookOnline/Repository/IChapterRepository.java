package com.NovelBookOnline.NovelBookOnline.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChapterRepository {
    Page<Chapter> getNewUpdateChapter(Pageable pageable);
    List<Chapter> getTop25HottestChapters();
    Page<Chapter> getChaptersByNovelId(String novelId, Pageable pageable);
    Chapter findChapterById(String id);
    void delete(Chapter chapter);
    void save(Chapter chapter);
    boolean existsByChapterNameAndChapterNumber(String chapterName, int chapterNumber);
}
