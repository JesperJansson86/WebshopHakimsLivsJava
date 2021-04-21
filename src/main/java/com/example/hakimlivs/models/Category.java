package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:11
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @OneToMany (cascade = {CascadeType.DETACH})
    @JoinColumn(name = "category_id")
    private List<Product> products;

    public Category(String category) {
        this.category = category;
    }
}