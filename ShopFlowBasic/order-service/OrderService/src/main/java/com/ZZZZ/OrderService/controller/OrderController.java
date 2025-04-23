package com.ZZZZ.OrderService.controller;


import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.response.ApiResponse;
import com.ZZZZ.OrderService.DTO.response.OrderDetailResponse;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.base.exception.ErrorCode;
import com.ZZZZ.OrderService.service.OrderService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CircuitBreakerRegistry circuitBreakerRegistry;


    @GetMapping("/roles")
    public void getRoles(@AuthenticationPrincipal Jwt jwt) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Roles: " + auth.getAuthorities());
    }

    @GetMapping("/cb-status")
    public String checkCBStatus() {
        io.github.resilience4j.circuitbreaker.CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("orderService");
        return cb.getState().name(); // Ví dụ: CLOSED, OPEN, HALF_OPEN
    }

    @GetMapping("/by-product")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByProductId(
            @RequestParam String productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {

        return orderService.getOrdersByProductId(productId, page, size, sortBy).getContent();
    }


    @GetMapping("/by-user")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDetailResponse> getOrdersByUserId(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {

        return orderService.getOrdersByUserId(userId, page, size, sortBy).getContent();
    }


    @GetMapping("/by-user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<OrderDetailResponse>> getPageOrdersByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {

        return ApiResponse.<Page<OrderDetailResponse>>builder()
                .result(orderService.getOrdersByUserId(userId, page, size, sortBy))
                .build();
    }


    @GetMapping("/debug-auth")
    public void debugAuth(@RequestParam String userId) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("UserId from param = " + userId);
        System.out.println("Auth name = " + authName);
    }


    public ApiResponse<String> fallBackSync(Throwable t) {
        return ApiResponse.<String>builder()
                .code(ErrorCode.SERVER_OVERLOAD.getCode())
                .message("Fallback: " + t.getMessage())
                .build();
    }
    @PostMapping
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallBackOrder")
    @RateLimiter(name = "orderService", fallbackMethod = "fallBackOrder")
    @TimeLimiter(name = "orderService", fallbackMethod = "fallBackOrder")
    @Bulkhead(name = "orderService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallBackOrder")
    public CompletableFuture<ApiResponse<OrderDetailResponse>> createOrder(@RequestBody OrderCreationRequest request) {
        return CompletableFuture.supplyAsync(() -> ApiResponse.<OrderDetailResponse>builder()
                .result(orderService.createOrder(request))
                .build());
    }

    public CompletableFuture<ApiResponse<?>> fallBackOrder(Throwable throwable) {
        return CompletableFuture.completedFuture(
             ApiResponse.builder()
                     .code(ErrorCode.SERVER_OVERLOAD.getCode())
                     .message(ErrorCode.SERVER_OVERLOAD.getMessage())
                     .build()
        );
    }
}
