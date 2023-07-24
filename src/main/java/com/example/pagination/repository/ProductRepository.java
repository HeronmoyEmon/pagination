package com.example.pagination.repository;

import com.example.pagination.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findAllBy(Pageable pageable);
    @Query("""
    select count(*) from products;
    """)
    Mono<Integer> getTotalCount();
}
