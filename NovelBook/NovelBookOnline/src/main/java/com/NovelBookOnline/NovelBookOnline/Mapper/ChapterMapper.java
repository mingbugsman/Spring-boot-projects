package com.NovelBookOnline.NovelBookOnline.Mapper;


import com.NovelBookOnline.NovelBookOnline.DTO.Response.Chapter.ChapterSummaryDTO;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChapterMapper {


    @Mapping(target = "chapterId", source = "id")
    ChapterSummaryDTO toSummaryEntity(Chapter chapter);
}
