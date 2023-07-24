package com.example.pagination.mvc;

import com.example.pagination.entity.Product;
import com.example.pagination.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Mono<Page<Product>> getProducts(@RequestParam("page") int page,
                                           @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getProducts(pageRequest);
    }

    @GetMapping("/products-flux")
    public Flux<Product> getProductsFlux(@RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getProductsFlux(pageRequest);
    }

    @GetMapping("/products-tuple")
    public Mono<Tuple2<List<Product>, Integer>> getProductsTuple(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getProductsTuple(pageRequest);
    }
}
