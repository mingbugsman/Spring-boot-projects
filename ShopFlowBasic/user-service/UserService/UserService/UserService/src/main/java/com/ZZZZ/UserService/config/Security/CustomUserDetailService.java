package com.ZZZZ.UserService.config.Security;



import com.ZZZZ.UserService.base.exception.ApplicationException;
import com.ZZZZ.UserService.base.exception.ErrorCode;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);
        if (user == null) {
            throw new ApplicationException(ErrorCode.EMAIL_NOT_EXISTED);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getName())))
                .collect(Collectors.toList());
    }
}
