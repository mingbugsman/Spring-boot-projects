package com.NovelBookOnline.NovelBookOnline.Mapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Author.AuthorRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Author.AuthorResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "novels", ignore = true)
    Author toEntity(AuthorRequest request);

    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateAuthor(@MappingTarget Author author, AuthorRequest request);


    AuthorResponse toEntityResponse(Author author);
}
