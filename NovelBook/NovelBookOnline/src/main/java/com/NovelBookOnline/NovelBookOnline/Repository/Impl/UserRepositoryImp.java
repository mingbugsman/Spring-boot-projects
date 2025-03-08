package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
@RequiredArgsConstructor

public class UserRepositoryImp  implements IUserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        User foundUser= userJpaRepository.findByEmail(email).orElse(null);
        return foundUser != null && foundUser.getDeletedAt() != null;
    }

    @Override
    public boolean existsById(String id) {
        return findUserById(id) != null;
    }

    @Override
    public User findUserById(String id) {
        return userJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public User findUserByUsername(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("NOT FOUND EMAIL"));
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        user.setDeletedAt(LocalDateTime.now());
    }
}
