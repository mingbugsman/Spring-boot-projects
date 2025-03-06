package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeAuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeCommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Like.LikeResponse;


public interface ILikeService {

        // update status like (upvote/down vote) của Author, Comment, Chapter
        LikeResponse updateLikeStatusAuthor(LikeAuthorRequest request);
        LikeResponse updateLikeStatusComment(LikeCommentRequest request);
        LikeResponse updateLikeStatusChapter(LikeChapterRequest request);

        // create like for the first time when user click like (upvote/downvote)
        LikeResponse createLikeAuthor(LikeAuthorRequest request);
        LikeResponse createLikeComment(LikeCommentRequest request);
        LikeResponse createLikeChapter(LikeChapterRequest request);
}
