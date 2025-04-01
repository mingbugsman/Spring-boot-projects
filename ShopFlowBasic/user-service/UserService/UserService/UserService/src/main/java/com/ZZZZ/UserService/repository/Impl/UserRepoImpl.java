package com.ZZZZ.UserService.repository.Impl;


import com.ZZZZ.UserService.base.exception.ApplicationException;
import com.ZZZZ.UserService.base.exception.ErrorCode;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.repository.Jpa.UserJpaRepo;
import com.ZZZZ.UserService.repository.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepoImpl implements UserRepo {
    private UserJpaRepo userJpaRepo;

    @Override
    public void save(User user) {
        userJpaRepo.save(user);
    }


    @Override
    public void softDeleteUser(User user) {
        user.setDeleteAt(LocalDateTime.now());
        userJpaRepo.save(user);
    }

    @Override
    public void deleteUserByAdmin(User user) {
        userJpaRepo.delete(user);
    }

    @Override
    public User getUser(String id) {
        Optional<User> foundUser = userJpaRepo.findByIdAndDeleteAtIsNull(id);
        return foundUser.orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public boolean existsUserByEmail(String email) {
        User foundUser = userJpaRepo.findByEmailAndDeleteAtIsNull(email);
        return foundUser != null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userJpaRepo.findByEmailAndDeleteAtIsNull(email);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userJpaRepo.findByDeleteAtIsNull(pageable);
    }

}