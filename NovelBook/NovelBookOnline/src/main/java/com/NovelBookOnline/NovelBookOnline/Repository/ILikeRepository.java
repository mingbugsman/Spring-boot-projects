package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeComment;

public interface ILikeRepository {

    // CHECKING
    LikeComment findLikeComment(String userId, String commentId);
    LikeAuthor findLikeAuthor(String userId, String authorId);
    LikeChapter findLikeChapter(String userId, String chapterId);

    // Save
    void saveLikeComment(LikeComment likeComment);
    void saveLikeChapter(LikeChapter likeChapter);
    void saveLikeAuthor(LikeAuthor likeAuthor);
}
