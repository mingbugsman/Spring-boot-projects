package com.MMMM.gateaway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;


import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.core.Ordered;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().toString();
        String method = exchange.getRequest().getMethod().toString();
        String headers = exchange.getRequest().getHeaders().toString();
        String params = exchange.getRequest().getQueryParams().toString();

        log.info("Incoming request: {} {} {}", method, requestPath, params);
        log.info("Header: {}", headers);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            int statusCode = exchange.getResponse().getStatusCode().value();
            log.info("Response status code: {}", statusCode);
            System.out.println("Response status: " + statusCode);
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}