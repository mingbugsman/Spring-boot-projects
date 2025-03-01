package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;

public interface IRefreshTokenRepository {
    RefreshToken findByToken(String refreshToken);
    void save(RefreshToken refreshToken);
    boolean checkExpiredRefreshToken(String refreshToken);
    boolean existsByToken(String token);
    void deleteTokenByUser(User user);


}
