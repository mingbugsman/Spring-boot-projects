package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface INovelRepository {
    Page<Novel> findNovelsByKeyword(String keyword, Pageable pageable);
    Page<Novel> getNewUpdateNovels(Pageable pageable);
    List<Novel> getTrendingNovel();
    Novel findNovelById( String id);
    Novel save(Novel novel);
    void deleteNovel(Novel novel);
    boolean existsById(String id);
    boolean existsByAuthorIdAndNovelName(String authorId, String novelName);
}
