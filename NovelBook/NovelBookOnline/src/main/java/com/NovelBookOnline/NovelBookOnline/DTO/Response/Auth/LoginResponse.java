package com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth;

public record LoginResponse (
    boolean isSuccessful,
    String refreshToken
){}
