package com.example.pagination.router;

import com.example.pagination.handler.ProductHandler;
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
                .GET("/products-page", productHandler::getProductsPage)
                .GET("/products-flux", productHandler::getProductsFlux)
                .GET("/products-tuple", productHandler::getProductsTuple)
                .build();
    }
}
