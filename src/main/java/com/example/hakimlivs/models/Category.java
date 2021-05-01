package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private List<Product> products;

    public Category(String category) {
        this.category = category;
    }
}