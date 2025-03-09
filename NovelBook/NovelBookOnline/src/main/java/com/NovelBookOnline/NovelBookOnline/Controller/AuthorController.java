package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    IAuthorService authorService;

    @GetMapping
    public ApiResponse<Page<AuthorSummaryResponse>> getAllAuthors(
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return ApiResponse.<Page<AuthorSummaryResponse>>builder()
                .message("successful")
                .result(authorService.getAllAuthors(sortOrder, page, size))
                .build();
    }

    @GetMapping("/top-rating")
    public ApiResponse<Page<AuthorSummaryResponse>> getTopAuthorByLike(
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<Page<AuthorSummaryResponse>>builder()
                .result(authorService.getTopAuthorByLike(sortOrder, page, size))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<Page<AuthorSummaryResponse>> findAuthorsByKeyWord(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<Page<AuthorSummaryResponse>>builder()
                .result(authorService.findAuthorsByKeyWord(keyword,sortOrder, page, size))
                .build();
    }

    @PostMapping
    public ApiResponse<AuthorDetailResponse> createAuthor(
            @RequestBody AuthorRequest request
            ) {
        return ApiResponse.<AuthorDetailResponse>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    @PutMapping("/edit/{authorId}")
    public ApiResponse<AuthorDetailResponse> updateAuthor(
            @PathVariable String authorId,
            @RequestBody AuthorRequest request
    ) {
        return ApiResponse.<AuthorDetailResponse>builder()
                .result(authorService.updateAuthor(authorId, request))
                .build();
    }

    @DeleteMapping("/delete/{authorId}")
    public ApiResponse<String> deleteAuthor(@PathVariable String authorId) {
        authorService.deleteAuthor(authorId);
        return ApiResponse.<String>builder()
                .result("successfully deleted id : " + authorId)
                .build();
    }

}
