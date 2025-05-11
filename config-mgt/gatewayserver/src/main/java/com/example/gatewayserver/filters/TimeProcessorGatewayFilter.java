package com.example.gatewayserver.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class TimeProcessorGatewayFilter extends AbstractGatewayFilterFactory<TimeProcessorGatewayFilter.Config> {

    public TimeProcessorGatewayFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        private Boolean enable;
    }

    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Pre-Filter for working with Reqeust
            Long startTime = System.currentTimeMillis();
            ServerHttpRequest request = exchange.getRequest();
            request.mutate().header("X-Added", "DONE");
            exchange.mutate().request(request);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Post Filter for working with Response
                if (config.getEnable()) {
                    Long endTime = System.currentTimeMillis();
                    Long processTime = endTime - startTime;
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().add("X-Process-Time", processTime.toString() + " ms");
                    exchange.mutate().response(response);
                }
            }));
        };
    }
}
