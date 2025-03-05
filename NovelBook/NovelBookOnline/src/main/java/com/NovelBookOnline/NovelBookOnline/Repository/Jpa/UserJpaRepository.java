package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,String> {

    @Query(value = """
            SELECT u FROM users u
            WHERE username = :username AND u.deleted_at IS NULL
            """, nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = """
            SELECT u FROM users u
            WHERE username = :email AND u.deleted_at IS NULL
            """, nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

}
