package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Role;
import com.NovelBookOnline.NovelBookOnline.Enum.TypeRole;


public interface IRoleRepository {
    void save(Role role);
    Role findByTypeRole(TypeRole typeRole);
}
