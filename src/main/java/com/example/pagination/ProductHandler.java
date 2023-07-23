package com.example.pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductHandler {

    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    Mono<ServerResponse> getProductsPage(ServerRequest serverRequest) {
        PageRequest pageRequest = buildPageRequest(serverRequest);
        return productService
                .getProducts(pageRequest)
                .flatMap(products -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(products));
    }

    Mono<ServerResponse> getProductsFlux(ServerRequest serverRequest) {
        PageRequest pageRequest = buildPageRequest(serverRequest);
        return productService
                .getProductsFlux(pageRequest)
                .collectList()
                .flatMap(products -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(products));
    }

    Mono<ServerResponse> getProductsTuple(ServerRequest serverRequest) {
        PageRequest pageRequest = buildPageRequest(serverRequest);
        return productService
                .getProductsTuple(pageRequest)
                .flatMap(products -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(products));
    }

    private PageRequest buildPageRequest(ServerRequest serverRequest) {
        int page = Integer.parseInt(String.valueOf(serverRequest.queryParam("page")));
        int size = Integer.parseInt(String.valueOf(serverRequest.queryParam("size")));

        return PageRequest.of(page, size);
    }
 }
