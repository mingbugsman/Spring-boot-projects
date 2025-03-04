package com.NovelBookOnline.NovelBookOnline.Mapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "userImageData", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    User toEntity(RegisterRequest request);


    @Mapping(target = "userImageData", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);



}
