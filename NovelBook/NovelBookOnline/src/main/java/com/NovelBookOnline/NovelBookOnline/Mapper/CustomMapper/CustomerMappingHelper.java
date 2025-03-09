package com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategoryDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.CommentResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Comment.ListCommentResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.*;
import com.NovelBookOnline.NovelBookOnline.Helper.CountHelper;
import com.NovelBookOnline.NovelBookOnline.Mapper.ChapterMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public final class CustomerMappingHelper {
    ChapterMapper chapterMapper;

    // AUTHOR

    // Summary
    public AuthorSummaryResponse toAuthorSummaryResponse(Author author) {
        return new AuthorSummaryResponse(
                author.getId(),
                author.getAuthorName(),
                Base64.getEncoder().encodeToString(author.getAuthorAvatar()),
                author.getAuthorDescription()
        );
    }
    // Detail
    public AuthorDetailResponse toAuthorDetailResponse(Author author) {
        return new AuthorDetailResponse(
                author.getId(),
                author.getAuthorName(),
                Base64.getEncoder().encodeToString(author.getAuthorAvatar()),
                author.getAuthorDescription(),
                author.getLikeAuthors().size(),
                CountHelper.countTotalReadingNovelsOfAuthor(author),
                author.getNovels().stream().map(this::toNovelSummary).toList()
        );
    }

    // USER

    // Summary
    public UserSummaryResponse toSummaryUser(User user) {
        return new UserSummaryResponse(
                user.getId(),
                user.getUsername(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getCreatedAt()
        );
    }
    // Update response
    public UserUpdateResponse toUserUpdateResponse(User user) {
        return new UserUpdateResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getGender(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getUpdatedAt()
        );
    }
    // Detail
    public UserDetailResponse toUserDetail(User user) {
        Author author = user.getAuthor();
        return new UserDetailResponse(
                user.getId(),
                user.getUsername(),
                Base64.getEncoder().encodeToString(user.getUserImageData()),
                user.getGender(),
                user.getLikes().size(),
                user.getComments().size(),
                author == null ? null : author.getNovels().stream().map(this::toNovelSummary).toList()
        );
    }


    // NOVEL

    // Summary
    public NovelSummaryResponse toNovelSummary(Novel novel) {
        return new NovelSummaryResponse(
                novel.getId(),
                novel.getNovelName(),
                Base64.getEncoder().encodeToString(novel.getNovelCoverImage()),
                novel.getCreatedAt()
        );
    }

    // Detail
    public NovelDetailResponse toNovelDetail(Novel novel) {
        String base64Data = Base64.getEncoder().encodeToString(novel.getNovelCoverImage());

        List<String> categoryNames = novel.getCategories().stream().map(Category::getCategoryName).toList();

        int totalReading = CountHelper.countTotalReadingOfNovel(novel);

        int totalLikes = CountHelper.countTotalLikeOfNovel(novel);

        List<CommentResponse> allComments = novel.getChapters()
                .stream()
                .filter(c -> c.getDeletedAt() != null)
                .map(Chapter::getComments)   // list comments for chapter
                .flatMap(Collection::stream)
                .map(this::toCommentResponse)
                .toList();

        return new NovelDetailResponse(
                novel.getId(),
                base64Data,
                novel.getNovelName(),
                novel.getNovelDescription(),
                novel.getAuthor().getAuthorName(),
                categoryNames,
                totalReading,
                totalLikes,
                novel.getChapters().stream().map(this::toChapterSummaryResponse).toList(),
                allComments
        );
    }


    // COMMENT
    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getUser().getUsername(),
                comment.getContent(),
                Base64.getEncoder().encodeToString(comment.getFileDataComment()),
                comment.getLikeComments().size(),
                CountHelper.countSubComment(comment)
        );
    }

    public ListCommentResponse toListCommentResponse(Chapter chapter) {
        return new ListCommentResponse(
                chapter.getId(),
                chapter.getComments().stream().filter(c-> c.getDeletedAt() != null).map(this::toCommentResponse).toList()
        );
    }

    // CATEGORY
    public CategoryDetailResponse toCategoryDetailResponse(Category category) {
        int totalNovelsOfCategory = category.getNovels().size();
        List<NovelSummaryResponse> novels = category
                .getNovels()
                .stream().map(this::toNovelSummary).toList();
        return new CategoryDetailResponse(
                category.getId(),
                category.getCategoryName(),
                category.getCategoryInformation(),
                totalNovelsOfCategory,
                novels
        );
    }



    // CHAPTER : SUMMARY - DETAIL
    public ChapterSummaryResponse toChapterSummaryResponse(Chapter chapter) {
        Novel novel = chapter.getNovel();
        return new ChapterSummaryResponse(
                chapter.getId(),
                novel.getNovelName(),
                Base64.getEncoder().encodeToString(novel.getNovelCoverImage()),
                chapter.getChapterNumber()
        );
    }

    public ChapterDetailResponse toChapterDetailResponse(Chapter chapter) {
        return new ChapterDetailResponse(
                chapter.getId(),
                CountHelper.countTotalReadingOfChapter(chapter),
                chapter.getChapterName(),
                chapter.getChapterNumber(),
                chapter.getChapterContent(),
                chapter.getLikes().size(),
                toListCommentResponse(chapter)
        );
    }
}
