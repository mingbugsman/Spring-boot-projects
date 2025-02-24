package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INovelRepository extends JpaRepository<User,String> {
}
