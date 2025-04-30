package com.hh.api_gateway;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoutes() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates
                .path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoutes(){
        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoutes() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates
                        .path("/api/inventory"), HandlerFunctions.http("http://localhost:8083"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker",
                        URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoutes(){
        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8083"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoutes() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates
                        .path("/api/orders"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker",
                        URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoutes(){
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse>fallbackRoutes(){
        return GatewayRouterFunctions.route("fallback")
                .GET("/fallback", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable, Please try again later"))
                .build();
    }
}
