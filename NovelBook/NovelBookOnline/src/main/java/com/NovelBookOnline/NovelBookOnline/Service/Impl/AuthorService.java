package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Mapper.AuthorMapper;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Repository.IAuthorRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService implements IAuthorService {

    IAuthorRepository authorRepository;
    AuthorMapper authorMapper;
    CustomerMappingHelper customerMappingHelper;

    @Override
    public AuthorDetailResponse createAuthor(AuthorRequest request) {
        Author author = authorMapper.toEntity(request);
        authorRepository.save(author);
        return customerMappingHelper.toAuthorDetailResponse(author);
    }

    @Override
    public AuthorDetailResponse updateAuthor(String authorId, AuthorRequest request) {
        Author author = authorRepository.getAuthor(authorId);
        authorMapper.updateAuthor(author, request);
        authorRepository.save(author);
        return customerMappingHelper.toAuthorDetailResponse(author);
    }

    @Override
    public Page<AuthorSummaryResponse> getAllAuthors(SortOrder sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorRepository.getAllAuthors(sortOrder.name(), pageable).map(customerMappingHelper::toAuthorSummaryResponse);
    }

    @Override
    public Page<AuthorSummaryResponse> getTopAuthorByLike(SortOrder sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorRepository.getTopAuthorByLike(sortOrder.name(),pageable).map(customerMappingHelper::toAuthorSummaryResponse);
    }

    @Override
    public Page<AuthorSummaryResponse> findAuthorsByKeyWord(String keyword,SortOrder sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorRepository.findAuthorsByKeyWord(keyword,pageable).map(customerMappingHelper::toAuthorSummaryResponse);
    }

    @Override
    public void deleteAuthor(String authorId) {
        Author author = authorRepository.getAuthor(authorId);
        authorRepository.delete(author);
    }
}
