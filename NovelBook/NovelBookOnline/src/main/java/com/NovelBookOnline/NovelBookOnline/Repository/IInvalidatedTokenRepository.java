package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.InvalidatedToken;

public interface IInvalidatedTokenRepository {
    boolean existsById(String id);
    void save(InvalidatedToken invalidatedToken);
}
