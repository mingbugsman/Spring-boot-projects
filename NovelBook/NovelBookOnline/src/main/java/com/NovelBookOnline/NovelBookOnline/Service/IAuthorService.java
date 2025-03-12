package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface IAuthorService {
    AuthorDetailResponse createAuthor(AuthorRequest request) throws IOException;
    AuthorDetailResponse updateAuthor(String authorId, AuthorRequest request) throws IOException;
    Page<AuthorSummaryResponse> getAllAuthors(SortOrder sortOrder, int page, int size);
    Page<AuthorSummaryResponse> getTopAuthorByLike(SortOrder sortOrder, int page, int size);
    Page<AuthorSummaryResponse> findAuthorsByKeyWord(String keyword, SortOrder sortOrder, int page, int size);
    void deleteAuthor(String authorId);

}
