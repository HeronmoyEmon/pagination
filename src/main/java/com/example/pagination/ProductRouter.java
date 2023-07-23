package com.example.pagination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> porductRouterConfig(ProductHandler productHandler) {
        return RouterFunctions
                .route()
                .GET("/products", productHandler::getProductsPage)
                .GET("/products-flux", productHandler::getProductsFlux)
                .GET("/products-tuple", productHandler::getProductsTuple)
                .build();
    }
}