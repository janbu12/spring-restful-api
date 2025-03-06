package com.example.restful.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kategori_id")
    private Integer id;

    @Column(name="nama_kategori")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;
}
