package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;

public interface IAuthenticationService {
    void logout(HttpServletRequest httpServletRequest, LogoutRequest logRequest);
    AuthenticationResponse authenticate(HttpServletRequest httpServletRequest, LoginRequest loginRequest);
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    RegisterResponse register(RegisterRequest request);
}
