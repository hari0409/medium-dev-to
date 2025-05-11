package com.example.gatewayserver.filters;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class HeaderLoggingGlobalFilter implements GlobalFilter {

    public static final Logger logger = LoggerFactory.getLogger(HeaderLoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Pre-Filter for Request Manipulation
        exchange.getRequest().getHeaders().forEach((key, value) -> {
            logger.info("Header {} Value: {}", key, value);
        });
        // Request Mutation must be done with a copy
        ServerHttpRequest request = exchange.getRequest();
        String uniqueRequestId = UUID.randomUUID().toString();
        request.mutate().header("X-Gateway", uniqueRequestId);
        exchange.mutate().request(request);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Post-Filter for Response Manipulation
            logger.info("Request Status: {}", exchange.getResponse().getStatusCode());
            ServerHttpResponse response = exchange.getResponse();
            // Response Mutation via Mutation
            response.getHeaders().add("X-Gateway", uniqueRequestId);
            exchange.mutate().response(response);
            // Response Manipuation via Direct Change
            exchange.getResponse().setStatusCode(HttpStatus.ACCEPTED);
        }));
    }

}
