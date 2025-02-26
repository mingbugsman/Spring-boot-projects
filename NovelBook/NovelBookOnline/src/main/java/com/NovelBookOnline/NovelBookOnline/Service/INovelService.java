package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import org.springframework.data.domain.Page;


public interface INovelService {
    Page<NovelSummaryResponse> findNovelsByPagination( SortOrder sortOrder, int page, int size);
    Page<NovelSummaryResponse> searchNovelsByKeyword(String keyword, int page, int size, SortOrder sortOrder);

    NovelDetailResponse getDetailNovel(String id);

    NovelSummaryResponse createNovel(NovelRequest creationRequest);
    NovelSummaryResponse updateNovel(String id, NovelRequest updateRequest);
    void deleteNovel(String id);
}
