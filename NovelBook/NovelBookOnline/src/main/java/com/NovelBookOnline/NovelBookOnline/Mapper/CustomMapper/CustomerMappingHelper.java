package com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.*;
import com.NovelBookOnline.NovelBookOnline.Mapper.ChapterMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public final class CustomerMappingHelper {
    ChapterMapper chapterMapper;

    public UserSummaryResponse toSummaryUser(User user) {
        return new UserSummaryResponse(
                user.getId(),
                user.getUsername(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getCreatedAt()
        );
    }
    public UserUpdateResponse toUpdateResponse(User user) {
        return new UserUpdateResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getGender(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getUpdatedAt()
        );
    }
    public UserDetailResponse toUserDetail(User user) {

        return new UserDetailResponse(
                user.getId(),
                user.getUsername(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getGender(),
                user.getLikes().size(),
                user.getComments().size(),
                user.getNovels().stream().map(this::toNovelSummary).toList()
        );
    }

    public NovelSummaryResponse toNovelSummary(Novel novel) {
        return new NovelSummaryResponse(
                novel.getId(),
                novel.getNovelName(),
                Base64.getEncoder().encodeToString(novel.getNovelCoverImage()),
                novel.getCreatedAt()
        );
    }
    public NovelDetailResponse toNovelDetail(Novel novel) {
        String base64Data = Base64.getEncoder().encodeToString(novel.getNovelCoverImage());
        List<String> categoryNames = novel.getCategories().stream().map(Category::getCategoryName).toList();
        int totalReading = novel.getChapters().stream().map(Chapter::getTotalReadChapter).mapToInt(Integer::intValue).sum();
        int totalLikes = novel.getChapters().stream().map(Chapter::getLikes).map(List::size).reduce(0, Integer::sum);
        List<CommentResponse> allComments = novel.getChapters().stream()
                .map(Chapter::getComments)   // list comments for chapter
                .flatMap(Collection::stream)
                .map(this::toCommentResponse)
                .toList();


        return new NovelDetailResponse(
                novel.getId(),
                base64Data,
                novel.getNovelName(),
                novel.getNovelDescription(),
                novel.getUser().getUsername(),
                categoryNames,
                totalReading,
                totalLikes,
                novel.getChapters().stream().map(chapterMapper::toSummaryEntity).toList(),
                allComments
        );
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getUser().getUsername(),
                comment.getContent(),
                Base64.getEncoder().encodeToString(comment.getFileDataComment())
        );
    }
}
