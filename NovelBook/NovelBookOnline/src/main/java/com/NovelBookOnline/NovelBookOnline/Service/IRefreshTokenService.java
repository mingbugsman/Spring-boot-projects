package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;

import java.util.UUID;

public interface IRefreshTokenService {
    RefreshToken generateRefreshToken(User user) ;
    void removeRefreshToken(String refreshToken);
    RefreshToken verifyToken(String token);
}
