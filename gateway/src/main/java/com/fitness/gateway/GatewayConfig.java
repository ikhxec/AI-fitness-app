package com.fitness.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Routes
                .route("user-service", r -> r
                        .path("/api/users/**")
                        .uri("lb://USER-SERVICE"))

                // Activity Service Routes
                .route("activity-service", r -> r
                        .path("/api/activities/**")
                        .uri("lb://ACTIVITY-SERVICE"))

                // AI Service Routes
                .route("ai-service", r -> r
                        .path("/api/recommendations/**")
                        .uri("lb://AI-SERVICE"))

                .build();
    }
}