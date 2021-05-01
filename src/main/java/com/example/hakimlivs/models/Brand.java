package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private List<Product> products; // private List<Product> products;

    public Brand(String brand) {
        this.brand = brand;
    }
}