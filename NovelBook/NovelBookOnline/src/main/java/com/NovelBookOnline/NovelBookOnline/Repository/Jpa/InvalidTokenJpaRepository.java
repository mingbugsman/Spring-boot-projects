package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenJpaRepository extends JpaRepository<InvalidToken,String> {

    @Query("""
        SELECT i
        FROM InvalidToken i
        WHERE i.jwtId = :jwtId
        AND i.revocationDate IS NOT NULL
    """)
    InvalidToken findRevokedToken(@Param("jwtId") String jwtId);


}
