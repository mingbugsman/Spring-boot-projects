package com.ZZZZ.UserService.repository.Jpa;

import com.ZZZZ.UserService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepo extends JpaRepository<Role, String> {

}
