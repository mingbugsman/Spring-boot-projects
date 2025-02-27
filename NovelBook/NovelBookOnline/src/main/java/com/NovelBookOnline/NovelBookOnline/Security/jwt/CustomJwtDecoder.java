package com.NovelBookOnline.NovelBookOnline.Security.jwt;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.IntrospectRequest;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    @NonFinal
    @Value("${jwt.SIGNER-KEY}")
    private String signerKey;

    @NonFinal
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Autowired
    private final JwtProvider jwtProvider;


    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = jwtProvider.introspect(IntrospectRequest.builder()
                    .token(token)
                    .build());

            if (!response.isValid())
                throw new JwtException("Token invalid");
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
