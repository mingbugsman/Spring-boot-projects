package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChapterService {
    Page<ChapterSummaryResponse> getChaptersByNovelId(String novelId, int page, int size );
    ChapterDetailResponse getChapterDetail(String id);
    List<ChapterSummaryResponse> getTop25HottestChapters();
    Page<ChapterSummaryResponse> getNewUpdateChapterDaily(int page, int size);
    ChapterSummaryResponse addChapter(String novelId, ChapterRequest request);
    ChapterSummaryResponse updateChapter(String id, ChapterRequest request);
    void deleteChapter(String id);
}
