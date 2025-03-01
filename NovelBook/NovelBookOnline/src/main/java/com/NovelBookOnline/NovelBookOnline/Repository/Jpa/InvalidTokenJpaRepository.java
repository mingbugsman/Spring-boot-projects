package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenJpaRepository extends JpaRepository<InvalidToken,String> {

    @Query(value = """
        SELECT EXISTS (
            SELECT 1 FROM invalid_token
            WHERE access_token = :accessToken AND revocation_date IS NOT NULL
        )
    """, nativeQuery = true)
    boolean checkRevocationToken(String accessToken);

}
