package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unit;
    private String longUnit;

    @OneToMany (cascade = {CascadeType.PERSIST})//@OneToMany (cascade = {CascadeType.DETACH})
    @JoinColumn(name = "product_id") // unit_id
    @JsonBackReference
    private List<Product> products;


    public Unit(String unit, String longUnit) {
        this.unit = unit;
        this.longUnit = longUnit;
    }
}