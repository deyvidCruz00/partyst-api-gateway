package com.partyst.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    private static final String LOAD_BALANCER_URI = "https://partyst-loadbalancer.onrender.com";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta para autenticación (registro, login, logout)
                .route("auth_route", r -> r
                        .path("/auth/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway-Request", "true")
                                .addResponseHeader("X-Gateway-Response", "true")
                                .retry(config -> config
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET, HttpMethod.POST)
                                ))
                        .uri(LOAD_BALANCER_URI))
                
                // Ruta para API general
                .route("api_route", r -> r
                        .path("/api/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway-Request", "true")
                                .addResponseHeader("X-Gateway-Response", "true")
                                .retry(config -> config
                                        .setRetries(2)
                                        .setMethods(HttpMethod.GET)))
                        .uri(LOAD_BALANCER_URI))
                
                // Ruta para proyectos
                .route("projects_route", r -> r
                        .path("/projects/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway-Request", "true")
                                .addResponseHeader("X-Gateway-Response", "true"))
                        .uri(LOAD_BALANCER_URI))
                
                // Ruta para usuarios
                .route("users_route", r -> r
                        .path("/users/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway-Request", "true")
                                .addResponseHeader("X-Gateway-Response", "true"))
                        .uri(LOAD_BALANCER_URI))
                
                .build();
    }
}
