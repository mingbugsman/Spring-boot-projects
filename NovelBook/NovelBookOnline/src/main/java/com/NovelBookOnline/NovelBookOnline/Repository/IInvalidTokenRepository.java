package com.NovelBookOnline.NovelBookOnline.Repository;

public interface IInvalidTokenRepository {
    void revokeAccessToken(String jwtId);
    boolean checkRevocationToken(String jwtId);
}
