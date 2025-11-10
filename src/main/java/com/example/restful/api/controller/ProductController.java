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

/**
 * REST controller that provides endpoints for managing and retrieving {@link Product} data.
 * <p>
 * This controller exposes endpoints to fetch product information in the form of
 * {@link ProductDTO} objects. It communicates with the {@link ProductService} layer
 * to obtain product data from the database or data source.
 * </p>
 *
 * <p><b>Base URL:</b> /api/products</p>
 *
 * @author Mizan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all available products in the system.
     * <p>
     * This method fetches a list of {@link Product} entities from the {@link ProductService},
     * converts them into {@link ProductDTO} objects for cleaner API responses, and returns
     * the final list.
     * </p>
     *
     * @return a list of {@link ProductDTO} representing all available products.
     */
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getProducts();
        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}
