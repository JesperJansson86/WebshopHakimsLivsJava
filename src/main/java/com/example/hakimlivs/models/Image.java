package com.example.hakimlivs.models;

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

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Image(String image, Product product) {
        this.image = image;
        this.product = product;
    }
}