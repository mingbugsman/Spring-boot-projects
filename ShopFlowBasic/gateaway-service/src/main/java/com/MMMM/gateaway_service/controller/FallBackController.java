package com.MMMM.gateaway_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @GetMapping("/roles")
    public Mono<Object> getRoles(@AuthenticationPrincipal Jwt jwt) {
        Object roles = jwt.getClaimAsMap("realm_access").get("roles");
        return Mono.just(roles);
    }

    @GetMapping("/fallback/product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.ok("product Service is currently unavailable. Please try again later.");
    }
}

