package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;


public interface INovelService {
    Page<NovelSummaryResponse> searchNovelsByKeyword(String keyword, int page, int size, SortOrder sortOrder);
    List<NovelSummaryResponse> getTrendingNovels();
    NovelDetailResponse getDetailNovel(String id);
    Page<NovelSummaryResponse> getNovelsByListCategoryName(List<String> listCategoryName, int page, int size);
    NovelSummaryResponse createNovel(NovelRequest creationRequest) throws IOException;
    NovelSummaryResponse updateNovel(String id, NovelUpdateRequest updateRequest) throws IOException;
    void deleteNovel(String id);
}
