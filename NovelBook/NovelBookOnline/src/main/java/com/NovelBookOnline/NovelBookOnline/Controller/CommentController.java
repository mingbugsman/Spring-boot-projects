package com.NovelBookOnline.NovelBookOnline.Controller;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Comment.CommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import com.NovelBookOnline.NovelBookOnline.Service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommentResponse> addComment(@ModelAttribute CommentRequest request) {
        return ResponseEntity.ok(commentService.addComment(request));
    }

    @GetMapping("/{chapterId}")
    public ResponseEntity<Page<CommentResponse>> getAllCommentsByChapter(@PathVariable String chapterId,
                                                                         @RequestParam(defaultValue = "1") int pageNumber,
                                                                         @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(commentService.getAllCommentsByChapterId(chapterId, pageNumber, pageSize));
    }

    @GetMapping("/{commentId}/sub")
    public ResponseEntity<Page<CommentResponse>> getAllSubComments(@PathVariable String commentId,
                                                                   @RequestParam(defaultValue = "1") int pageNumber,
                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(commentService.getAllSubCommentsByParentComment(commentId, pageNumber, pageSize));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
