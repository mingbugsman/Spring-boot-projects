package com.ZZZZ.UserService.service;

import com.ZZZZ.UserService.DTO.Request.LogoutRequest;
import com.ZZZZ.UserService.DTO.Request.RefreshAccessTokenRequest;
import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;

import java.util.Map;

public interface AuthService {
    UserResponse register(UserCreationRequest request);
    Map<String, Object> login(UserCreationRequest request);
    Map<String, Object> refreshToken(RefreshAccessTokenRequest request);
    boolean logout(LogoutRequest request);
}
