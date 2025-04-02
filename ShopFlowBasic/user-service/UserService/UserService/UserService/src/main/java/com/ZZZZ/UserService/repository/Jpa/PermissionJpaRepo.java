package com.ZZZZ.UserService.repository.Jpa;

import com.ZZZZ.UserService.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepo extends JpaRepository<Permission, String> {

}
