package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    void logout(LogoutRequest request);
    AuthenticationResponse authenticate(LoginRequest request);
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    RegisterResponse register(RegisterRequest request);
}
