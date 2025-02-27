package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LoginRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.LogoutRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RefreshTokenRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.AuthenticationResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.RegisterResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.InvalidatedToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidatedTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Security.jwt.JwtProvider;
import com.NovelBookOnline.NovelBookOnline.Service.IAuthenticationService;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    IUserService userService;
    IUserRepository userRepository;
    IInvalidatedTokenRepository invalidatedTokenRepository;
    JwtProvider jwtProvider;

    @Override
    public void logout(LogoutRequest request) {
        try {
            String token = request.getToken();
            var signToken = jwtProvider.validateJwtToken(token,false);
            String jwtId = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jwtId)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findUserByEmail(request.getEmail());

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        String token = jwtProvider.generateToken(user);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var signJWT = jwtProvider.validateJwtToken(request.getToken(), true);
        var claimsSet = signJWT.getJWTClaimsSet();
        var jwtID = claimsSet.getJWTID();
        var expiryTime = claimsSet.getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jwtID)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

        var username = claimsSet.getSubject();
        var user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        var token = jwtProvider.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (!userService.createNewUser(request)) {
            throw new RuntimeException("Email already is exists");
        }
        User user = userRepository.findUserByEmail(request.getEmail());
        String token = jwtProvider.generateToken(user);
        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail(), token, user.getCreatedAt());
    }
}
