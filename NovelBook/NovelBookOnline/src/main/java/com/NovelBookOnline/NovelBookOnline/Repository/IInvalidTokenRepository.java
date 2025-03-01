package com.NovelBookOnline.NovelBookOnline.Repository;

public interface IInvalidTokenRepository {
    void revokeAccessToken(String token);
    boolean checkRevocationToken(String token);
}
