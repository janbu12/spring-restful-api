package com.example.restful.api.controller;

import com.example.restful.api.entity.Product;
import com.example.restful.api.model.ProductDTO;
import com.example.restful.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getProducts();
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
