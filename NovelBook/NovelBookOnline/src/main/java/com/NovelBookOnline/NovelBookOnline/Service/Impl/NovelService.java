package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Mapper.NovelMapper;
import com.NovelBookOnline.NovelBookOnline.Repository.IAuthorRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.ICategoryRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.INovelRepository;
import com.NovelBookOnline.NovelBookOnline.Service.INovelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class NovelService implements INovelService {
    IAuthorRepository authorRepository;
    INovelRepository novelRepository;
    ICategoryRepository categoryRepository;
    NovelMapper novelMapper;
    CustomerMappingHelper customMappingHelper;




    @Override
    public Page<NovelSummaryResponse> searchNovelsByKeyword(String keyword, int page, int size, SortOrder sortOrder) {
        Pageable pageable = PageRequest.of(page, size);
        return novelRepository.findNovelsByKeyword(keyword, pageable).map(customMappingHelper::toNovelSummary);
    }

    @Override
    public List<NovelSummaryResponse> getTrendingNovels() {
        return novelRepository.getTrendingNovel().stream().map(customMappingHelper::toNovelSummary).toList();
    }


    @Override
    public NovelDetailResponse getDetailNovel(String id) {
        return customMappingHelper.toNovelDetail(novelRepository.findNovelById(id));
    }

    @Override
    public Page<NovelSummaryResponse> getNovelsByListCategoryName(List<String> listCategoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return novelRepository.NovelWithCategoryName(listCategoryName, pageable, listCategoryName.size()).map(customMappingHelper::toNovelSummary);
    }

    @Override
    public NovelSummaryResponse createNovel(NovelRequest creationRequest) throws IOException {
        if (novelRepository.existsByAuthorIdAndNovelName(creationRequest.getAuthorId(), creationRequest.getNovelName())) {
            throw new ApplicationException(ErrorCode.NOVEL_EXISTED);
        }
        byte[] dataImage = creationRequest.getNovelCoverImage().getBytes();
        Novel novel = novelMapper.toEntity(creationRequest);
        novel.setAuthor(authorRepository.getAuthor(creationRequest.getAuthorId()));

        List<Category> categories = creationRequest.getCategoryIds().stream().map(categoryRepository::getCategory).toList();
        novel.setCategories(new HashSet<>(categories));

        novel.setNovelCoverImage(dataImage);
        novelRepository.save(novel);
        return customMappingHelper.toNovelSummary(novel);
    }

    @Override
    public NovelSummaryResponse updateNovel(String id, NovelUpdateRequest updateRequest) throws IOException {
        if (!novelRepository.existsById(id)) {
            throw new ApplicationException(ErrorCode.NOVEL_NOT_EXISTED);
        }
        Novel novel = novelRepository.findNovelById(id);
        novelMapper.updateEntity(novel, updateRequest);
        if (updateRequest.getNovelCoverImage() != null) {
            System.out.println("Co ton tai file");
            var dataImage = updateRequest.getNovelCoverImage().getBytes();
            novel.setNovelCoverImage(dataImage);
        }
        return customMappingHelper.toNovelSummary(novelRepository.save(novel));
    }

    @Override
    public void deleteNovel(String id) {
        Novel novel = novelRepository.findNovelById(id);
        novelRepository.deleteNovel(novel);
    }
}
