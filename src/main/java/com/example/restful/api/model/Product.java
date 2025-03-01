package com.example.restful.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
}
