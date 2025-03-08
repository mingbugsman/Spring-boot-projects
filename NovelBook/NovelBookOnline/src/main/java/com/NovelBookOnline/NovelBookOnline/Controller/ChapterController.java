package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Chapter.ChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Service.IChapterService;
import com.NovelBookOnline.NovelBookOnline.Service.IDailyReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {
    private final IChapterService chapterService;
    private final IDailyReadingService dailyReadingService;

    @GetMapping("/detail/{chapterId}")
    public ResponseEntity<ChapterDetailResponse> getChapterDetail(
            @PathVariable String chapterId
    ) {
        dailyReadingService.recordRead(chapterId);
        return ResponseEntity.ok(chapterService.getChapterDetail(chapterId));
    }

    @GetMapping("/hottest")
    public ResponseEntity<List<ChapterSummaryResponse>> getTop25HottestChapters() {
        return ResponseEntity.ok(chapterService.getTop25HottestChapters());
    }

    @GetMapping("/updates")
    public ResponseEntity<Page<ChapterSummaryResponse>> getNewUpdateChapterDaily(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return ResponseEntity.ok(chapterService.getNewUpdateChapterDaily(page ,size));
    }

    @PutMapping("/{chapterId}")
    public ResponseEntity<ChapterSummaryResponse> updateChapter(
            @PathVariable String chapterId,
            @RequestBody ChapterRequest request
            ) {
        return ResponseEntity.ok(chapterService.updateChapter(chapterId, request));
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<String> deleteChapter(@PathVariable String chapterId) {
        chapterService.deleteChapter(chapterId);
        return ResponseEntity.ok("successfully deleted chapter with id : " + chapterId);
    }

}
