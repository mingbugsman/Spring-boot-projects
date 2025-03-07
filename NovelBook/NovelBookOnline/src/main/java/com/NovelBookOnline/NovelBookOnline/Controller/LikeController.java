package com.NovelBookOnline.NovelBookOnline.Controller;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeAuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeCommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Like.LikeResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.LikeStatus;
import com.NovelBookOnline.NovelBookOnline.Service.ILikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeController {
    ILikeService likeService;

    @PostMapping("/author/{authorId}")
    public ResponseEntity<LikeResponse> voteAuthor(
            @PathVariable String authorId,
            @RequestParam LikeStatus likeStatus) {
        LikeAuthorRequest request = new LikeAuthorRequest(authorId, likeStatus);
        return ResponseEntity.ok(likeService.updateLikeStatusAuthor(request));
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<LikeResponse> voteComment(
           @PathVariable String commentId,
           @RequestParam LikeStatus likeStatus
            ) {
        LikeCommentRequest request = new LikeCommentRequest(commentId, likeStatus);
        return ResponseEntity.ok(likeService.updateLikeStatusComment(request));
    }

    @PostMapping("/chapter/{chapterId}")
    public ResponseEntity<LikeResponse> voteChapter(
            @PathVariable String chapterId,
            @RequestParam LikeStatus likeStatus
    ) {
        LikeChapterRequest request = new LikeChapterRequest(chapterId, likeStatus);
        return ResponseEntity.ok(likeService.updateLikeStatusChapter(request));
    }

}
