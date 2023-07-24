package com.example.pagination.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {
    @Id
    private Long id;
    private String name;
}
