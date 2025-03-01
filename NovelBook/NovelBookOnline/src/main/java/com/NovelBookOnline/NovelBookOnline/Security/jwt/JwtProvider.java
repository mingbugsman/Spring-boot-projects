package com.NovelBookOnline.NovelBookOnline.Security.jwt;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.IntrospectRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.IntrospectResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IRefreshTokenRepository;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider {
    IInvalidTokenRepository blackList;

    @NonFinal
    @Value("${jwt.SIGNER-KEY}")
    protected String jwtSecret;

    @NonFinal
    @Value("${jwt.accessTokenExpirationMs}")
    protected long accessTokenExpirationMs;


    public SignedJWT validateAccessToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jwtID = signedJWT.getJWTClaimsSet().getJWTID();

        // Check if the token is on the blacklist (has it been revoked yet)
        if (blackList.checkRevocationToken(jwtID)) {
            throw new IllegalArgumentException("TOKEN REVOKED");
        }

        // check signature
        JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());
        if (!signedJWT.verify(verifier)) {
            throw new IllegalArgumentException("INVALID TOKEN");
        }

        // Check the expiration time
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expiryTime.before(new Date())) {
            throw new IllegalArgumentException("TOKEN EXPIRED");
        }

        return signedJWT;
    }


    public IntrospectResponse introspect(IntrospectRequest request)throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = validateAccessToken(token) != null;

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public String generateAccessToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = createPayLoad(user);
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(jwtSecret.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }


    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        System.out.println(user.toString());
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getTypeRole());
            });
        }
        System.out.println("Generated scope: " + stringJoiner);
        return stringJoiner.toString();
    }

    private Payload createPayLoad(User user) {
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Tran Tuan Minh")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(accessTokenExpirationMs, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        return new Payload(jwtClaimsSet.toString());
    }


}
