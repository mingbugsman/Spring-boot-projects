package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeAuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeCommentRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Like.LikeResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeComment;
import com.NovelBookOnline.NovelBookOnline.Enum.LikeStatus;
import com.NovelBookOnline.NovelBookOnline.Mapper.LikeMapper;
import com.NovelBookOnline.NovelBookOnline.Repository.ILikeRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Security.SecurityUtils;
import com.NovelBookOnline.NovelBookOnline.Service.ILikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeService implements ILikeService {
    IUserRepository userRepository;
    ILikeRepository likeRepository;
    LikeMapper likeMapper;

    private String getCurrentUserId() {
        String userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new RuntimeException("Unauthorized user");
        }
        return userId;
    }

    @Override
    public LikeResponse updateLikeStatusAuthor(LikeAuthorRequest request) {
        String userId = getCurrentUserId();

        LikeAuthor likeAuthor = likeRepository.findLikeAuthor(userId, request.getAuthorId());
        if (likeAuthor != null) {
            likeAuthor.setLikeStatus(request.getLikeStatus());
            likeRepository.saveLikeAuthor(likeAuthor);
        } else {
            return createLikeAuthor(request);
        }

        return toLikeResponse(request.getLikeStatus());
    }

    @Override
    public LikeResponse updateLikeStatusComment(LikeCommentRequest request) {
        String userId = getCurrentUserId();

        LikeComment likeComment = likeRepository.findLikeComment(userId, request.getCommentId());
        if (likeComment != null) {
            likeComment.setLikeStatus(request.getLikeStatus());
            likeRepository.saveLikeComment(likeComment);
        } else {
            return createLikeComment(request);
        }

        return toLikeResponse(request.getLikeStatus());
    }

    @Override
    public LikeResponse updateLikeStatusChapter(LikeChapterRequest request) {
        String userId = getCurrentUserId();

        LikeChapter likeChapter = likeRepository.findLikeChapter(userId, request.getChapterId());
        if (likeChapter != null) {
            likeChapter.setLikeStatus(request.getLikeStatus());
            likeRepository.saveLikeChapter(likeChapter);
        } else {
            return createLikeChapter(request);
        }

        return toLikeResponse(request.getLikeStatus());
    }


    private LikeResponse createLikeAuthor(LikeAuthorRequest request) {
        String userId = getCurrentUserId();

        LikeAuthor likeAuthor = likeMapper.toLikeAuthor(request);
        likeAuthor.setUser(userRepository.findUserById(userId));
        likeRepository.saveLikeAuthor(likeAuthor);

        return toLikeResponse(request.getLikeStatus());
    }


    private LikeResponse createLikeComment(LikeCommentRequest request) {
        String userId = getCurrentUserId();

        LikeComment likeComment = likeMapper.toLikeComment(request);
        likeComment.setUser(userRepository.findUserById(userId));
        likeRepository.saveLikeComment(likeComment);

        return toLikeResponse(request.getLikeStatus());
    }

    private LikeResponse createLikeChapter(LikeChapterRequest request) {
        String userId = getCurrentUserId();

        LikeChapter likeChapter = likeMapper.toLikeChapter(request);
        likeChapter.setUser(userRepository.findUserById(userId));
        likeRepository.saveLikeChapter(likeChapter);

        return toLikeResponse(request.getLikeStatus());
    }

    private LikeResponse toLikeResponse(LikeStatus likeStatus) {
        return new LikeResponse(likeStatus);
    }

}
