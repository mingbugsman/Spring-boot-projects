package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,String> {
}
