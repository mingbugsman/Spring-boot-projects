package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.User;

public interface IUserRepository {
    boolean existsByEmail(String email);
    boolean existsById(String id);
    User findUserById(String id);
    void save(User user);
    void deleteUser(User user);
}
