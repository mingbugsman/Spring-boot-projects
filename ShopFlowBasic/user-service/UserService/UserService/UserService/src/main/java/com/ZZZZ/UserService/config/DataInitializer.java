package com.ZZZZ.UserService.config;


import com.ZZZZ.UserService.entity.Permission;
import com.ZZZZ.UserService.entity.Role;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.repository.Jpa.PermissionJpaRepo;
import com.ZZZZ.UserService.repository.Jpa.RoleJpaRepo;
import com.ZZZZ.UserService.repository.Jpa.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserJpaRepo userRepository;
    @Autowired
    private RoleJpaRepo roleRepository;
    @Autowired
    private PermissionJpaRepo permissionRepository;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    @Override
    public void run(String... args) throws Exception {
        // init role and admin
        if (userRepository.findByUsername("admin") == null) {
            List<Permission> permissionAdminList = new ArrayList<>();
            List<Permission> permissionUserList = new ArrayList<>();

            Permission READ_DATA_USERS = new Permission();
            READ_DATA_USERS.setName("READ_DATA_USERS");
            READ_DATA_USERS.setDescription("allows read public data users");
            permissionAdminList.add(READ_DATA_USERS);
            permissionUserList.add(READ_DATA_USERS);

            Permission READ_DETAIL_DATA_USER = new Permission();
            READ_DETAIL_DATA_USER.setName("READ_DETAIL_DATE_USER");
            READ_DETAIL_DATA_USER.setDescription("allows read detail data users (time order, their product)");
            permissionAdminList.add(READ_DETAIL_DATA_USER);

            Permission DELETE_USERS = new Permission();
            DELETE_USERS.setName("DELETE_USER");
            DELETE_USERS.setDescription("allows delete users");
            permissionAdminList.add(DELETE_USERS);

            Permission UPDATE_USERS = new Permission();
            UPDATE_USERS.setName("UPDATE_USER");
            UPDATE_USERS.setDescription("allows update users");
            permissionAdminList.add(UPDATE_USERS);

            Permission CREATE_USERS = new Permission();
            CREATE_USERS.setName("CREATE_USERS");
            CREATE_USERS.setDescription("allows create users");
            permissionAdminList.add(CREATE_USERS);

            Role ROLE_USER = new Role();
            ROLE_USER.setName("ROLE_USER");
            ROLE_USER.setPermissions(new HashSet<>(permissionUserList));


            Role ROLE_ADMIN = new Role();
            ROLE_ADMIN.setName("ROLE_ADMIN");
            ROLE_ADMIN.setPermissions(new HashSet<>(permissionAdminList));

            permissionRepository.saveAll(permissionAdminList);
            roleRepository.save(ROLE_ADMIN);
            roleRepository.save(ROLE_USER);

            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("adminApplicationShop@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("123"));
            adminUser.setUpdateAt(null);
            userRepository.save(adminUser);
            System.out.println("password admin is not security, pls change it");
        }
    }
}