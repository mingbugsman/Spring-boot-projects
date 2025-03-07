package com.NovelBookOnline.NovelBookOnline.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
public interface IChapterRepository {
    Chapter findChapterById(String id);
}
