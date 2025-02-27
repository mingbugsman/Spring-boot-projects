package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.User;

public interface IUserRepository {

    boolean existsByEmail(String email);
    boolean existsById(String id);
    User findUserById(String id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    void save(User user);
    void deleteUser(User user);
}
