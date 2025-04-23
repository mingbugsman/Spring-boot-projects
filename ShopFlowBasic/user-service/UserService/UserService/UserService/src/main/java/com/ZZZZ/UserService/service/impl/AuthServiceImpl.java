package com.ZZZZ.UserService.service.impl;

import com.ZZZZ.UserService.DTO.Request.LogoutRequest;
import com.ZZZZ.UserService.DTO.Request.RefreshAccessTokenRequest;
import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.base.exception.ApplicationException;
import com.ZZZZ.UserService.base.exception.ErrorCode;
import com.ZZZZ.UserService.base.util.Generator;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.kafka.UserEventProducer;
import com.ZZZZ.UserService.mapper.UserMapper;
import com.ZZZZ.UserService.repository.UserRepo;
import com.ZZZZ.UserService.service.AuthService;
import com.ZZZZ.UserService.service.KeycloakService;
import com.ZZZZ.commonDTO.EmailRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepo userRepo;
    UserMapper userMapper;
    UserEventProducer userEventProducer;
    KeycloakService keyCloakService;

    @Override
    public UserResponse register(UserCreationRequest request) {
        System.out.println("CREATE USER:::" + request);
        if (userRepo.existsUserByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setUsername( Generator.generatorUsername());

        // hash password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String keycloakId = keyCloakService.createUserInKeycloak(request);
        user.setId(keycloakId);

        userRepo.save(user);
        userEventProducer.sendUserOTPEmailEvent(new EmailRequest(request.getEmail(), user.getUsername()));
        return userMapper.toUserResponse(user);
    }

    @Override
    public Map<String, Object> login(UserCreationRequest request) {

        User user = userRepo.getUserByEmail(request.getEmail());
        if (user == null) {
            throw new ApplicationException(ErrorCode.UNAUTHENTICATED);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isMatched = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if (!isMatched) {
            throw new ApplicationException(ErrorCode.PASSWORD_INVALID);
        }
        return keyCloakService.loginAndGetTokens(request.getEmail(), request.getPassword());
    }

    @Override
    public Map<String, Object> refreshToken(RefreshAccessTokenRequest request) {
        if (request.getRefreshToken().isEmpty() || request.getRefreshToken().isBlank()) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED);
        }
        return keyCloakService.refreshAccessToken(request.getRefreshToken());
    }

    @Override
    public boolean logout(LogoutRequest request) {
        return keyCloakService.logout(request.getRefreshToken());
    }


}
