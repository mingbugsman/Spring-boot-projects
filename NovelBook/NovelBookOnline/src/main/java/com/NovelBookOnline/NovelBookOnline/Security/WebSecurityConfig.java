package com.NovelBookOnline.NovelBookOnline.Security;

import com.NovelBookOnline.NovelBookOnline.Security.jwt.CustomJwtDecoder;
import com.NovelBookOnline.NovelBookOnline.Security.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @NonFinal
    private final String[] PUBLIC_AUTH_ENDPOINT = {
            "/api/auth/register",
            "/api/auth/login",
            "/api/auth/introspect",
            "/api/auth/refresh-token"};

    @NonFinal
    private final String[] PUBLIC_NOVEL_ENDPOINT = {
            "/api/novels/trending",
            "/api/novels/{id}",
            "/api/novels/search",
            "/api/novels/{novelId}/chapters",
            "/api/novels/{novelId}/categories",
            "/api/novels/search/by-category-names"
    };
    @NonFinal
    private final String[] PUBLIC_CHAPTER_ENDPOINT = {
            "/api/chapters/detail/{chapterId}",
            "/api/chapters/hottest",
            "/api/chapters/updates"
    };

    @NonFinal
    private final String[] PUBLIC_CATEGORY_ENDPOINT = {
            "/api/categories"
    };

    @NonFinal
    private final String[] PUBLIC_COMMENT_ENDPOINT = {
            "/api/comments/{chapterId}",
            "/sub/{commentId}"
    };

    @NonFinal
    private final String[] PUBLIC_AUTHOR_ENDPOINT = {
            "/api/authors",
            "/api/authors/top-rating",
            "/api/authors/search"
    };

    @NonFinal
    private final String[] PUBLIC_USER_ENDPOINT = {
            "/api/users/profile/{id}",
            "/api/users/summary/{id}"
    };

    private CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,PUBLIC_AUTH_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_NOVEL_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_CHAPTER_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_CATEGORY_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_COMMENT_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_AUTHOR_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_USER_ENDPOINT).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .decoder(customJwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        return http.build();
    }


    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =  new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

}
