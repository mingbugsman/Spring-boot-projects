package com.ZZZZ.UserService.config;


import com.ZZZZ.UserService.repository.Jpa.PermissionJpaRepo;
import com.ZZZZ.UserService.repository.Jpa.RoleJpaRepo;
import com.ZZZZ.UserService.repository.Jpa.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer  {

    @Autowired
    private UserJpaRepo userRepository;
    @Autowired
    private RoleJpaRepo roleRepository;
    @Autowired
    private PermissionJpaRepo permissionRepository;


}