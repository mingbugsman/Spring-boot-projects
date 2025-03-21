package com.NovelBookOnline.NovelBookOnline.Mapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "authorAvatar", ignore = true)
    @Mapping(target = "likeAuthors", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Author toEntity(AuthorRequest request);

    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorAvatar", ignore = true)
    @Mapping(target = "likeAuthors", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateAuthor(@MappingTarget Author author, AuthorRequest request);

}
