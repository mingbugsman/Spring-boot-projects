package com.ZZZZ.UserService.repository;

import com.ZZZZ.UserService.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserRepo {
    void save(User user);
    void softDeleteUser(User user);
    void deleteUserByAdmin(User user);
    User getUser(String id);
    User getUserByEmail(String email);
    Page<User> getAll(Pageable pageable);
}
