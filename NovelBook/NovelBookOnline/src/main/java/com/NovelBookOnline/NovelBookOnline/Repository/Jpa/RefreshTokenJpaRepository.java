package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, String> {

    @Query("""
            SELECT rf FROM RefreshToken rf
            WHERE rf.token = :refreshToken AND rf.expirationTime IS NULL
            """)
    boolean checkExpirationToken(@Param("token") String refreshToken);

    boolean existsByToken(String token);
    Optional<RefreshToken> findByToken(String refreshToken);

    void deleteByUser(User user);
}
