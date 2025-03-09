package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Service.IChapterService;
import com.NovelBookOnline.NovelBookOnline.Service.IDailyReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {
    private final IChapterService chapterService;
    private final IDailyReadingService dailyReadingService;

    @GetMapping("/detail/{chapterId}")
    public ApiResponse<ChapterDetailResponse> getChapterDetail(
            @PathVariable String chapterId
    ) {
        dailyReadingService.recordRead(chapterId);
        return ApiResponse.<ChapterDetailResponse>builder()
                .result(chapterService.getChapterDetail(chapterId))
                .build();
    }

    @GetMapping("/hottest")
    public ApiResponse<List<ChapterSummaryResponse>> getTop25HottestChapters() {
        return ApiResponse.<List<ChapterSummaryResponse>>builder()
                .result(chapterService.getTop25HottestChapters())
                .build();
    }

    @GetMapping("/updates")
    public ApiResponse<Page<ChapterSummaryResponse>> getNewUpdateChapterDaily(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return ApiResponse.<Page<ChapterSummaryResponse>>builder()
                .result(chapterService.getNewUpdateChapterDaily(page, size))
                .build();
    }

    @PutMapping("/{chapterId}")
    public ApiResponse<ChapterSummaryResponse> updateChapter(
            @PathVariable String chapterId,
            @RequestBody ChapterRequest request
            ) {
        return ApiResponse.<ChapterSummaryResponse>builder()
                .result(chapterService.updateChapter(chapterId, request))
                .build();
    }

    @DeleteMapping("/{chapterId}")
    public ApiResponse<String> deleteChapter(@PathVariable String chapterId) {
        chapterService.deleteChapter(chapterId);
        return ApiResponse.<String>builder()
                .result("Successfully deleted chapter id : " + chapterId)
                .build();
    }

}
