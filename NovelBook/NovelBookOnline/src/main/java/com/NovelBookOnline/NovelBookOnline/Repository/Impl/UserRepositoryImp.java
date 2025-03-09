package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
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
    public User findUserById(String id) {
        return userJpaRepository.findById(id).orElseThrow(() ->new ApplicationException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public User findUserByUsername(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_EXISTED));
    }

    @Override
    public User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.EMAIL_NOT_EXISTED));
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
