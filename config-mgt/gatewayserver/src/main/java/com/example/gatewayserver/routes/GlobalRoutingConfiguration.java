package com.example.gatewayserver.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gatewayserver.filters.TimeProcessorGatewayFilter;
import com.example.gatewayserver.predicate.CustomGeoCheckPredicate;

@Configuration
public class GlobalRoutingConfiguration {
        @Bean
        public RouteLocator globalRouteLocator(RouteLocatorBuilder routeLocatorBuilder,
                        CustomGeoCheckPredicate geoCheckPredicate,
                        TimeProcessorGatewayFilter timeProcessorGatewayFilter) {
                return routeLocatorBuilder.routes()
                                .route("server_router", p -> p.path("/gateway/server/**")
                                                .filters(f -> f.rewritePath("/gateway/server/(?<segment>.*)",
                                                                "/${segment}")
                                                                .circuitBreaker(config -> config
                                                                                .setName("server-circuitbreaker")
                                                                                .setFallbackUri("forward:/server-fallback")))
                                                .uri("lb://SERVER"))
                                .build();
        }
}
