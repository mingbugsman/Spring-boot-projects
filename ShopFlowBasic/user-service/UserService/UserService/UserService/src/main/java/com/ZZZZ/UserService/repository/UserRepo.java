package com.ZZZZ.UserService.repository;


import com.ZZZZ.UserService.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserRepo {
    void save(User user);
    void softDeleteUser(User user);
    void deleteUserByAdmin(User user);
    User getUser(String id);
    boolean existsUserByEmail(String email);
    User getUserByEmail(String email);
    Page<User> getAll(Pageable pageable);
}