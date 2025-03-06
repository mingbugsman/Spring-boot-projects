package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeComment;
import com.NovelBookOnline.NovelBookOnline.Repository.ILikeRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.LikeAuthorJpaRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.LikeChapterJpaRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.LikeCommentJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeRepositoryImpl implements ILikeRepository {
    LikeCommentJpaRepository likeCommentJpaRepository;
    LikeAuthorJpaRepository likeAuthorJpaRepository;
    LikeChapterJpaRepository likeChapterJpaRepository;


    @Override
    public LikeComment findLikeComment(String userId, String commentId) {
        return likeCommentJpaRepository.findByUserIdAndCommentId(userId, commentId);
    }

    @Override
    public LikeAuthor findLikeAuthor(String userId, String authorId) {
        return likeAuthorJpaRepository.findByUserIdAndAuthorId(userId, authorId);
    }

    @Override
    public LikeChapter findLikeChapter(String userId, String chapterId) {
        return likeChapterJpaRepository.findByUserIdAndChapterId(userId, chapterId);
    }

    // SAVE
    @Override
    public void saveLikeComment(LikeComment likeComment) {
        likeCommentJpaRepository.save(likeComment);
    }

    @Override
    public void saveLikeChapter(LikeChapter likeChapter) {
        likeChapterJpaRepository.save(likeChapter);
    }

    @Override
    public void saveLikeAuthor(LikeAuthor likeAuthor) {
        likeAuthorJpaRepository.save(likeAuthor);
    }
}
