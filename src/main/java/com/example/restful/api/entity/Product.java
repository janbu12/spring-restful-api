package com.example.restful.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="produk_id")
    private Integer id;

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
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategori_id", insertable = false, updatable = false)
    @JsonBackReference
    private Category category;
}
