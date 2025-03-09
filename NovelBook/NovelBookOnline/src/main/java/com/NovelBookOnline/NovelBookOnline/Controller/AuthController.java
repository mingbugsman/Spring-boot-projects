package com.NovelBookOnline.NovelBookOnline.Controller;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest httpServletRequest, @RequestBody @Valid LogoutRequest logoutRequest) {
        authenticationService.logout(httpServletRequest, logoutRequest);
        return ApiResponse.<String>builder()
                .result("Successfully logout")
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(HttpServletRequest httpServletRequest, @Valid @RequestBody  LoginRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(httpServletRequest, request))
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.<RegisterResponse>builder()
                .result(authenticationService.register(request))
                .build();
    }

}
