package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Role;
import com.NovelBookOnline.NovelBookOnline.Enum.TypeRole;
import com.NovelBookOnline.NovelBookOnline.Repository.IRoleRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository {
    private final  RoleJpaRepository roleJpaRepository;
    @Override
    public void save(Role role) {
        roleJpaRepository.save(role);
    }

    @Override
    public Role findByTypeRole(TypeRole typeRole) {
        return roleJpaRepository.findByTypeRole(typeRole).orElse(null);
    }
}
