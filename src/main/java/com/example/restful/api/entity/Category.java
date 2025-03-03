package com.example.restful.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kategori_id")
    private Long id;

    @Column(name="nama_kategori")
    private String categoryName;
}
