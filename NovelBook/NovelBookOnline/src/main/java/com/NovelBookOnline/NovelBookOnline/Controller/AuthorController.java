package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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

    @PostMapping(value = "/register-author", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<AuthorDetailResponse> createAuthor(
            @ModelAttribute @Valid AuthorRequest request
            ) throws IOException {
        System.out.println(request.toString());
        return ApiResponse.<AuthorDetailResponse>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    @PutMapping(value = "/edit/{authorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<AuthorDetailResponse> updateAuthor(
            @PathVariable String authorId,
            @ModelAttribute @Valid AuthorRequest request
    ) throws IOException {
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
