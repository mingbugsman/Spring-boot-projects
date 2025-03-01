package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Role;
import com.NovelBookOnline.NovelBookOnline.Enum.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, String> {
    Optional<Role> findByTypeRole(TypeRole typeRole);
}
