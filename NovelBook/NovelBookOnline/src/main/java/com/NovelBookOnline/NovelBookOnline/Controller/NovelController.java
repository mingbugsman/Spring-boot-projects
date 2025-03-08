package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.INovelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/novels")
@RequiredArgsConstructor
public class NovelController {

    private final INovelService novelService;

    /**
     *
     */
    @GetMapping("/trending")
    public ResponseEntity<List<NovelSummaryResponse>> getTrendingNovels() {
        return ResponseEntity.ok(novelService.getTrendingNovels());
    }

    /**
     * Get the list of novels by pagination and sort order
     */
    @GetMapping
    public ResponseEntity<Page<NovelSummaryResponse>> getUpdateNovels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<NovelSummaryResponse> novels = novelService.getUpdateNovels(page, size);
        return ResponseEntity.ok(novels);
    }

    /**
     * search novel by keyword
     */
    @GetMapping("/search")
    public ResponseEntity<Page<NovelSummaryResponse>> searchNovels(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<NovelSummaryResponse> novels = novelService.searchNovelsByKeyword(keyword, page, size, sortOrder);
        return ResponseEntity.ok(novels);
    }

    /**
     * get detail novel by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<NovelDetailResponse> getNovelDetail(@PathVariable String id) {
        NovelDetailResponse novel = novelService.getDetailNovel(id);
        return ResponseEntity.ok(novel);
    }

    /**
     * add new novel
     */
    @PostMapping
    public ResponseEntity<NovelSummaryResponse> createNovel(@RequestBody @Valid NovelRequest request) throws IOException {
        NovelSummaryResponse novel = novelService.createNovel(request);
        return novel != null ? ResponseEntity.status(HttpStatus.CREATED).body(novel)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    /**
     * update novel by id
     */
    @PutMapping("/{id}")
    public ResponseEntity<NovelSummaryResponse> updateNovel(@PathVariable String id, @RequestBody @Valid NovelRequest request) throws IOException {
        NovelSummaryResponse updatedNovel = novelService.updateNovel(id, request);
        return updatedNovel != null ? ResponseEntity.ok(updatedNovel)
                : ResponseEntity.notFound().build();
    }

    /**
     * delete novel
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovel(@PathVariable String id) {
        novelService.deleteNovel(id);
        return ResponseEntity.noContent().build();
    }
}
