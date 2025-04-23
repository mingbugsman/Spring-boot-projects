package com.ZZZZ.UserService.controller;


import com.ZZZZ.UserService.DTO.Request.LogoutRequest;
import com.ZZZZ.UserService.DTO.Request.RefreshAccessTokenRequest;
import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Response.ApiResponse;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/auth/register")
    public ApiResponse<UserResponse> registerUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("user registered successfully")
                .result(authService.register(request))
                .build();
    }

    @PostMapping("/auth/login")
    public ApiResponse<Map<String,Object>> loginUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<Map<String, Object>>builder()
                .message("user login successfully")
                .result(authService.login(request))
                .build();
    }


    @PostMapping("/auth/refresh-token")
    public ApiResponse<Map<String,Object>> refreshToken(@RequestBody @Valid RefreshAccessTokenRequest request) {
        return ApiResponse.<Map<String, Object>>builder()
                .message("refresh token successfully")
                .result(authService.refreshToken(request))
                .build();
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Boolean> logout(@RequestBody @Valid LogoutRequest request) {
        boolean isLogin = authService.logout(request);
        return ApiResponse.<Boolean>builder()
                .result(isLogin)
                .message("Successfully logout")
                .build();
    }

}
