package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import java.util.List;

public interface IChapterService {
    ChapterDetailResponse getChapterDetail(String id);
    List<ChapterSummaryResponse> getTop25HottestChapters();
    List<ChapterSummaryResponse> getNewUpdateChapterDaily(int page, int size);
    ChapterSummaryResponse addChapter(ChapterRequest request);
    ChapterSummaryResponse updateChapter(String id, ChapterRequest request);
    void deleteChapter(String id);
}
