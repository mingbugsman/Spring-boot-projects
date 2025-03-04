package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Like;

public interface ILikeRepository {
    void likeChapter(Like like);
    void unLikeChapter(Like like);
}
