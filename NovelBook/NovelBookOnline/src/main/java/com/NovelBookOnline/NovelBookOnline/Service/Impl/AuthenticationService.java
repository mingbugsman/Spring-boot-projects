package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
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
        String accessToken = getAccessTokenFromHeader(httpServletRequest);
        if (accessToken == null) {
            throw new ApplicationException(ErrorCode.INVALID_AUTHORIZATION_HEADER);
        }
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
    public AuthenticationResponse authenticate(HttpServletRequest httpServletRequest, LoginRequest request) throws ParseException, JOSEException {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findUserByEmail(request.getEmail());

        // check password
        boolean isMatchedPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isMatchedPassword) {
            throw new ApplicationException(ErrorCode.PASSWORD_INVALID);
        }
        // check access token
        String existingAccessToken = getAccessTokenFromHeader(httpServletRequest);
        if (existingAccessToken != null) {
            var signToken = jwtProvider.validateAccessToken(existingAccessToken);
            String jwtId = signToken.getJWTClaimsSet().getJWTID();
            boolean isRevoked = blacklist.checkRevocationToken(jwtId);
            if (isRevoked) {
                throw new ApplicationException(ErrorCode.UNAUTHENTICATED);
            }
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
            throw new ApplicationException(ErrorCode.USER_NOT_EXISTED);
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
        userService.createNewUser(request);
 
        User user = userRepository.findUserByEmail(request.getEmail());
        String accessToken = jwtProvider.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail(), accessToken, refreshToken.getToken(), user.getCreatedAt());
    }
    private String getAccessTokenFromHeader(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
           return null;
        }
        return authorizationHeader.substring(7); // Cắt "Bearer "
    }
}
