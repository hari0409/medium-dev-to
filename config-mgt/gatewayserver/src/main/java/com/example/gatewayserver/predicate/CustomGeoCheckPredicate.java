package com.example.gatewayserver.predicate;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;

@Component
public class CustomGeoCheckPredicate implements RoutePredicateFactory<CustomGeoCheckPredicate.Config> {

    @Data
    public class Config {
        private List<String> allowedGeo;

        public Config() {

        }
    }

    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            List<String> headerValue = exchange.getRequest().getHeaders().get("X-Request-Geo");
            return headerValue != null && !headerValue.isEmpty() && config.getAllowedGeo().contains(headerValue.get(0));
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

}
