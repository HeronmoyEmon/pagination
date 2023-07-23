package com.example.pagination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Page<Product>> getProducts(PageRequest pageRequest) {
        return productRepository
                .findAllBy(pageRequest)
                .doOnNext(product -> log.info("Product Received : {}", product))
                .collectList()
                .zipWith(this.productRepository.count())
                .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
                /*.map(products -> PageableExecutionUtils.getPage(products, pageable, () -> 0));*/
    }

    public Flux<Product> getProductsFlux(PageRequest pageRequest) {
        return productRepository
                .findAllBy(pageRequest)
                .doOnNext(product -> log.info("Product Received : {}", product));
    }

    public Mono<Tuple2<List<Product>, Integer>> getProductsTuple(PageRequest pageRequest) {
        return productRepository
                .findAllBy(pageRequest)
                .collectList()
                .zipWith(productRepository.getTotalCount())
                .doOnNext(product -> log.info("Product Received : {}", product));
    }
}