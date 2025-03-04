package com.NovelBookOnline.NovelBookOnline.Config;

import com.NovelBookOnline.NovelBookOnline.Entity.Role;
import com.NovelBookOnline.NovelBookOnline.Enum.TypeRole;
import com.NovelBookOnline.NovelBookOnline.Repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RoleInit {

    private final IRoleRepository roleRepository;

    @Bean
    public ApplicationRunner initRole() {
        return args -> {
            List<TypeRole> roles = List.of(TypeRole.ADMIN, TypeRole.USER, TypeRole.GUEST,TypeRole.AUTHOR);

            for (TypeRole typeRole : roles) {
                if (roleRepository.findByTypeRole(typeRole) == null) {
                    Role role = new Role();
                    role.setTypeRole(typeRole);
                    roleRepository.save(role);
                }
            }
        };
    }
}
