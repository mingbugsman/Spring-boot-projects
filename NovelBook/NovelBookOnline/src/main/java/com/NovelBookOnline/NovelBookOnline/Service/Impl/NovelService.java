package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Mapper.NovelMapper;
import com.NovelBookOnline.NovelBookOnline.Repository.INovelRepository;
import com.NovelBookOnline.NovelBookOnline.Service.INovelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class NovelService implements INovelService {
    INovelRepository novelRepository;
    NovelMapper novelMapper;
    CustomerMappingHelper customMappingHelper;

    @Override
    public Page<NovelSummaryResponse> findNovelsByPagination( SortOrder sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<String> novelIds = novelRepository.findAllIds();
        List<NovelSummaryResponse> novels = novelRepository.findNovelsByIds(sortOrder, novelIds).stream().map(customMappingHelper::toNovelSummary).toList();;

        return new PageImpl<>(novels, pageable, novelIds.size());
    }

    @Override
    public Page<NovelSummaryResponse> searchNovelsByKeyword(String keyword, int page, int size, SortOrder sortOrder) {
        Pageable pageable = PageRequest.of(page, size);

        Page<String> novelIdsPage = novelRepository.findNovelIdsByKeyword(keyword, pageable);
        List<String> novelIds = novelIdsPage.getContent();

        List<NovelSummaryResponse> novels = novelRepository.findNovelsByIds(sortOrder, novelIds).stream().map(customMappingHelper::toNovelSummary).toList();

        return new PageImpl<>(novels, pageable, novelIdsPage.getTotalElements());
    }


    @Override
    public NovelDetailResponse getDetailNovel(String id) {
        return customMappingHelper.toNovelDetail(novelRepository.findNovelById(id));
    }

    @Override
    public NovelSummaryResponse createNovel(NovelRequest creationRequest) {
        if (novelRepository.existsByAuthorIdAndNovelName(creationRequest.getAuthor_id(), creationRequest.getNovelName())) {
            return null;
        }
        Novel novel = novelMapper.toEntity(creationRequest);
        novelRepository.save(novel);
        return customMappingHelper.toNovelSummary(novel);
    }

    @Override
    public NovelSummaryResponse updateNovel(String id, NovelRequest updateRequest) {
        if (!novelRepository.existsById(id)) {
            return null;
        }
        Novel novel = novelRepository.findNovelById(id);
        novelMapper.updateEntity(novel, updateRequest);
        return customMappingHelper.toNovelSummary(novelRepository.save(novel));
    }

    @Override
    public void deleteNovel(String id) {
        Novel novel = novelRepository.findNovelById(id);
        novelRepository.deleteNovel(novel);
    }
}
