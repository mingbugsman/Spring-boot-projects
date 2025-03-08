package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Mapper.ChapterMapper;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Repository.IChapterRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IChapterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterServiceImpl implements IChapterService {
    IChapterRepository chapterRepository;
    ChapterMapper chapterMapper;
    CustomerMappingHelper customerMappingHelper;

    @Override
    public ChapterDetailResponse getChapterDetail(String id) {
        return customerMappingHelper.toChapterDetailResponse(chapterRepository.findChapterById(id));
    }

    @Override
    public List<ChapterSummaryResponse> getTop25HottestChapters() {
        return chapterRepository.getTop25HottestChapters().stream().map(customerMappingHelper::toChapterSummaryResponse).toList();
    }

    @Override
    public List<ChapterSummaryResponse> getNewUpdateChapterDaily(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chapterRepository.getNewUpdateChapter(pageable).map(customerMappingHelper::toChapterSummaryResponse).toList();
    }

    @Override
    public ChapterSummaryResponse addChapter(ChapterRequest request) {
        if (chapterRepository.existsByChapterNameAndChapterNumber(request.getChapterName(), request.getChapterNumber())) {
            throw new IllegalArgumentException("already exists");
        }
        Chapter chapter = chapterMapper.toEntity(request);
        chapterRepository.save(chapter);
        return customerMappingHelper.toChapterSummaryResponse(chapter);
        
    }

    @Override
    public ChapterSummaryResponse updateChapter(String id, ChapterRequest request) {
        Chapter chapter = chapterRepository.findChapterById(id);
        chapterMapper.updateEntity(chapter, request);
        chapterRepository.save(chapter);
        return customerMappingHelper.toChapterSummaryResponse(chapter);
    }

    @Override
    public void deleteChapter(String id) {
        Chapter chapter = chapterRepository.findChapterById(id);
        chapterRepository.delete(chapter);
    }
}
