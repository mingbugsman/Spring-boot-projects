package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User,String> {
    boolean existsByEmail(String email);

}
