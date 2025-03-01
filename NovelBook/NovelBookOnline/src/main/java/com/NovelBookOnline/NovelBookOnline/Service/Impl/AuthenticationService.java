package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Security.jwt.JwtProvider;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthenticationService;
import com.NovelBookOnline.NovelBookOnline.Service.IRefreshTokenService;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    IUserService userService;
    IUserRepository userRepository;
    IRefreshTokenService refreshTokenService;
    IInvalidTokenRepository blacklist;
    JwtProvider jwtProvider;

    @Override
    @Transactional
    public void logout(HttpServletRequest httpServletRequest, LogoutRequest logoutRequest) {
        // 1. Lấy Access Token từ Header
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authorization header");
        }
        String accessToken = authorizationHeader.substring(7); // Cắt "Bearer "

        // 2. Thu hồi (BlackList) Access Token
        try {
            var signToken = jwtProvider.validateAccessToken(accessToken);
            String jwtId = signToken.getJWTClaimsSet().getJWTID();

            // Đưa vào blacklist
            blacklist.revokeAccessToken(jwtId);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException("Invalid token", e);
        }
        refreshTokenService.removeRefreshToken(logoutRequest.getRefreshToken());
    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findUserByEmail(request.getEmail());

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        String accessToken = jwtProvider.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .authenticated(true)
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var foundRefreshToken = refreshTokenService.verifyToken(request.getRefreshToken());

        var user = userRepository.findUserByUsername(foundRefreshToken.getUser().getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        String accessToken = jwtProvider.generateAccessToken(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(foundRefreshToken.getToken())
                .authenticated(true)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (!userService.createNewUser(request)) {
            throw new RuntimeException("Email already is exists");
        }
        User user = userRepository.findUserByEmail(request.getEmail());
        String accessToken = jwtProvider.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail(), accessToken, refreshToken.getToken(), user.getCreatedAt());
    }
}
