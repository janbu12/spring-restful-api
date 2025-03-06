package com.example.restful.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotNull
    @Size(max = 255)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private String namalengkap;

    @NotNull
    private String notelepon;

    @NotNull
    private String alamat;

    private Integer status;
}
