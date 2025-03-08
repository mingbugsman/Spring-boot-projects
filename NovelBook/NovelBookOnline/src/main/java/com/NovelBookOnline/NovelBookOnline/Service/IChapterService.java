package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;

import java.util.List;

public interface IChapterService {
    ChapterDetailResponse getChapterDetail(String id);
    List<ChapterSummaryResponse> getNewUpdateChapterDaily();
    ChapterSummaryResponse addChapter(ChapterRequest request);
    ChapterSummaryResponse updateChapter(ChapterRequest request);
    void deleteChapter(String id);
}
