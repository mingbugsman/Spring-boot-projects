package com.NovelBookOnline.NovelBookOnline.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;

import java.util.List;

public interface IChapterRepository {
    List<Chapter> getNewUpdateChapter();
    List<Chapter> getBestChapterOfTheWeek();
    List<Chapter> getChaptersByNovelId(String novelId);
    Chapter findChapterById(String id);
    void delete(Chapter chapter);
    void add(Chapter chapter);
}
