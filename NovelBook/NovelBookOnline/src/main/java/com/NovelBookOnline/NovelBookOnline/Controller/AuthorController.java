package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
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
    public ResponseEntity<Page<AuthorSummaryResponse>> getAllAuthors(
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return ResponseEntity.ok(authorService.getAllAuthors(sortOrder, page, size));
    }

    @GetMapping("/top-rating")
    public ResponseEntity<Page<AuthorSummaryResponse>> getTopAuthorByLike(
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(authorService.getTopAuthorByLike(sortOrder, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AuthorSummaryResponse>> findAuthorsByKeyWord(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "DESC")SortOrder sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(authorService.findAuthorsByKeyWord(keyword,sortOrder, page, size));
    }

    @PostMapping
    public ResponseEntity<AuthorDetailResponse> createAuthor(
            @RequestBody AuthorRequest request
            ) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @PutMapping("/edit/{authorId}")
    public ResponseEntity<AuthorDetailResponse> updateAuthor(
            @PathVariable String authorId,
            @RequestBody AuthorRequest request
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(authorId, request));
    }

    @DeleteMapping("/delete/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok("successfully deleted author");
    }

}
