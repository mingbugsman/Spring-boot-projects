package com.NovelBookOnline.NovelBookOnline.Security.jwt;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.IntrospectRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Auth.IntrospectResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.InvalidatedTokenJpaRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.security.cert.X509CertSelector;
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
    static  Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    InvalidatedTokenJpaRepository invalidatedTokenJpaRepository;

    @NonFinal
    @Value("${jwt. SIGNER-KEY}")
    protected String jwtSecret;

    @NonFinal
    @Value("${jwt.VALID-DURATION}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.REFRESHABLE-DURATION}")
    protected long REFRESHABLE_DURATION;

    public SignedJWT validateJwtToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if (!(verified) && expiryTime.after(new Date())) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        else if (invalidatedTokenJpaRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new IllegalArgumentException("UNAUTHENTICATED");
        }
        return signedJWT;
    }

    public IntrospectResponse introspect(IntrospectRequest request)throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            validateJwtToken(token, false);
        } catch (Exception e) {
            isValid = false;
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    public String generateToken(User user) {


        JWSHeader header = new JWSHeader(JWSAlgorithm.ES256);
        Payload payload = createPayLoad(user);
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(jwtSecret.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    public String getUsernameFromJwtToken(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        System.out.println(user.toString());
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
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
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        return new Payload(jwtClaimsSet.toString());
    }


}
