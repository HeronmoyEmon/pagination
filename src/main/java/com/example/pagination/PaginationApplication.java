package com.example.pagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class PaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaginationApplication.class, args);
    }

}
