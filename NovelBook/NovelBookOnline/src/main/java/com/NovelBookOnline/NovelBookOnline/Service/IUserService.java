package com.NovelBookOnline.NovelBookOnline.Service;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;

import java.io.IOException;

public interface IUserService {
    UserDetailResponse getProfileUserById(String id);
    UserSummaryResponse getSummaryUser(String id);
    boolean createNewUser(RegisterRequest request);
    UserUpdateResponse updateUser(String id,UserUpdateRequest request) throws IOException;
    void deleteUser(String id);
}
