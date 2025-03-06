package com.NovelBookOnline.NovelBookOnline.Mapper;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeAuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeChapterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Like.LikeCommentRequest;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LikeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LikeAuthor toLikeAuthor(LikeAuthorRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LikeComment toLikeComment(LikeCommentRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LikeChapter toLikeChapter(LikeChapterRequest request);


}
