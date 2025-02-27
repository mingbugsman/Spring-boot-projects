package com.NovelBookOnline.NovelBookOnline.Mapper;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "email")
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "userImageData", ignore = true)
    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    User toEntity(RegisterRequest request);


    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    @Mapping(target = "imageBase64", ignore = true)
    UserUpdateResponse toUpdateResponse(User user);



}
