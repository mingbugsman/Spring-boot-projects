package com.ZZZZ.UserService.repository.Jpa;

import com.ZZZZ.UserService.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, String> {
    Page<User> findByDeleteAtIsNull(Pageable pageable);
    User findByUsername(String username);
    Optional<User> findByIdAndDeleteAtIsNull(String id);
    User findByEmailAndDeleteAtIsNull(String email);

}