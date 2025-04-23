package com.ZZZZ.UserService.controller;



import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Request.VerifiedEmailRequest;
import com.ZZZZ.UserService.DTO.Response.ApiResponse;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.service.UserService;
import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/roles")
    public Mono<Object> getRoles(@AuthenticationPrincipal Jwt jwt) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        System.out.println("Authentication name:::" + auth.getName());

        Object roles = jwt.getClaimAsMap("realm_access").get("roles");
        return Mono.just(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(userService.getAll(page, size, sortBy));
    }

    @PostMapping("send-otp")
    public ApiResponse<String> sendOTP(@RequestBody @Valid EmailRequest request) {
        return ApiResponse.<String>builder()
                .message(userService.sendOTP(request))
                .build();
    }

    @PostMapping("verify-email")
    public ApiResponse<String> verifyEmail(@RequestBody VerifiedEmailRequest request) {
        boolean isVerified = userService.verifyEmail(request.getEmail(), request.getOtp());
        return ApiResponse.<String>builder()
                .result(isVerified ? "Your email is verified" : "Wrong otp")
                .build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateInformationUser(@PathVariable String id, @RequestBody @Valid UserUpdateInformationRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("Successfully deleted user id : " + id);
    }

    @DeleteMapping("/by-admin/{id}")
    public ResponseEntity<String> deleteByAdmin(@PathVariable String id) {
        userService.absoluteDeleteUser(id);
        return ResponseEntity.ok("Successfully deleted user id : " + id);
    }



}
