package com.example.gatewayserver.routes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import com.example.gatewayserver.filters.TimeProcessorGatewayFilter;
import com.example.gatewayserver.predicate.CustomGeoCheckPredicate;

@Configuration
public class GlobalRoutingConfiguration {
    @Bean
    public RouteLocator globalRouteLocator(RouteLocatorBuilder routeLocatorBuilder,
            CustomGeoCheckPredicate geoCheckPredicate, TimeProcessorGatewayFilter timeProcessorGatewayFilter) {
        return routeLocatorBuilder.routes()
                .route("server_router", p -> p.path("/gateway/server/**")
                        .and()
                        // Requires a Header X-Auth-Token following the given RegEx
                        .header("X-Auth-Token", "AUTH-TOKEN-.{10}$")
                        .and()
                        // Allows only GET & POST Methods
                        .method(HttpMethod.GET, HttpMethod.POST)
                        .and()
                        .predicate(geoCheckPredicate.apply(c -> {
                            c.setAllowedGeo(Arrays.asList("IN", "UK", "US", "CA"));
                        }))
                        .filters(f -> f.rewritePath("/gateway/server/(?<segment>.*)", "/${segment}")
                                // Adding a Reqeust Tracker
                                .addRequestHeader("X-Request-Tracker-Id", UUID.randomUUID().toString())
                                // Adding a Timestamp of when the request was processed.
                                .addResponseHeader("X-Timestamp-Processed-At", LocalDateTime.now().toString())
                                .filter(timeProcessorGatewayFilter.apply(c -> c.setEnable(true))))
                        .uri("lb://SERVER"))
                .build();
    }
}
