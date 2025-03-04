package com.NovelBookOnline.NovelBookOnline.Mapper;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Novel.NovelRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Novel.NovelSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NovelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalReading", ignore = true)
    @Mapping(target = "novelCoverImage", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "chapters", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "novelStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore =true)
    Novel toEntity(NovelRequest creationRequest);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalReading", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "chapters", ignore = true)
    @Mapping(target = "novelStatus", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore =true)
    @Mapping(target = "novelCoverImage", ignore = true)
    void updateEntity(@MappingTarget Novel novel, NovelRequest updateRequest);

}
