package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private Product product;

    public Image(String image, Product product) {
        this.image = image;
        this.product = product;
    }
}