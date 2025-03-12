package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.IChapterService;
import com.NovelBookOnline.NovelBookOnline.Service.INovelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/novels")
@RequiredArgsConstructor
public class NovelController {

    private final INovelService novelService;
    private final IChapterService chapterService;
    /**
     * getting trending novels
     */
    @GetMapping("/trending")
    public ApiResponse<List<NovelSummaryResponse>> getTrendingNovels() {
        return ApiResponse.<List<NovelSummaryResponse>>builder()
                .result(novelService.getTrendingNovels())
                .build();
    }
    /**
     * search novel by keyword
     */
    @GetMapping("/search")
    public ApiResponse<Page<NovelSummaryResponse>> searchNovels(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.<Page<NovelSummaryResponse>>builder()
                .result(novelService.searchNovelsByKeyword(keyword, page, size, sortOrder))
                .build();
    }

    /**
     * get detail novel by id
     */
    @GetMapping("/{id}")
    public ApiResponse<NovelDetailResponse> getNovelDetail(@PathVariable String id) {
        return ApiResponse.<NovelDetailResponse>builder()
                .result(novelService.getDetailNovel(id))
                .build();
    }

    /**
     * add new novel
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<NovelSummaryResponse> createNovel(@ModelAttribute @Valid NovelRequest request) throws IOException {
        return ApiResponse.<NovelSummaryResponse>builder()
                .result(novelService.createNovel(request))
                .build();
    }

    /**
     * update novel by id
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<NovelSummaryResponse> updateNovel(@PathVariable String id, @ModelAttribute @Valid NovelRequest request) throws IOException {
        return ApiResponse.<NovelSummaryResponse>builder()
                .result(novelService.updateNovel(id, request))
                .build();
    }

    /**
     * delete novel
     */
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteNovel(@PathVariable String id) {
        novelService.deleteNovel(id);
        return ApiResponse.<String>builder()
                .result("Successfully deleted id " + id)
                .build();
    }


    @GetMapping("/{novelId}/chapters")
    public ApiResponse<Page<ChapterSummaryResponse>> getChaptersByNovelId(
            @PathVariable String novelId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return ApiResponse.<Page<ChapterSummaryResponse>>builder()
                .result(chapterService.getChaptersByNovelId(novelId, page, size))
                .build();
    }


    @PostMapping("/{novelId}/chapters")
    public ApiResponse<ChapterSummaryResponse> addChapter(
            @PathVariable String novelId,
            @RequestBody ChapterRequest request
            ) {
        return ApiResponse.<ChapterSummaryResponse>builder()
                .result(chapterService.addChapter(novelId, request))
                .build();
    }


    @GetMapping("/search/by-category-names")
    public ApiResponse<Page<NovelSummaryResponse>> NovelWithCategoryName(@RequestBody List<String> listCategoryName,
                                                                            @RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.<Page<NovelSummaryResponse>>builder()
                .result(novelService.getNovelsByListCategoryName(listCategoryName, page, size))
                .build();
    }

}
