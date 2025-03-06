package com.example.restful.api.model;

import com.example.restful.api.entity.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private String imageUrl;
    private Integer categoryId;
    private String categoryName;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getCategoryName();
    }
}
