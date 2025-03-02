package com.example.restful.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name="nama_produk")
    private String name;

    @Column(name="harga_sewea")
    private Double price;

    @Column(name="deskripsi")
    private String description;

    @Column(name="stok")
    private Integer quantity;

    @Column(name="gambar")
    private String imageUrl;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="kategori_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategori_id", insertable = false, updatable = false)
    private Category category;
}
