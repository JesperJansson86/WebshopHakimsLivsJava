package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:03
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 */
@Data
@Entity
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    @OneToMany (cascade = {CascadeType.PERSIST}) // @OneTOMany , DETACH
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private List<Product> products; // private List<Product> products;

    public Brand(String brand) {
        this.brand = brand;
    }
}