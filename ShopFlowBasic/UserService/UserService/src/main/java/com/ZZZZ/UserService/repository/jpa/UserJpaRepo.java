package com.ZZZZ.UserService.repository.jpa;

import com.ZZZZ.UserService.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, String> {
    Page<User> findByDeleteAtIsNull(Pageable pageable);


    Optional<User> findByIdAndDeleteAtIsNull(String id);
    Optional<User> findByEmailAndDeleteAtIsNull(String email);

}
