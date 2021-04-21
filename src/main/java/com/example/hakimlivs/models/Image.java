package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:31
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private String image;
    @JsonManagedReference
    @ManyToOne (cascade = {CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;

    public Image(String image, Product product) {
        this.image = image;
        this.product = product;
    }
}