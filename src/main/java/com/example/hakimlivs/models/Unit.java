package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:08
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
@NoArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private String unit;
     
    private String longUnit;

    @OneToMany (cascade = {CascadeType.DETACH})
    @JoinColumn(name = "unit_id")
    private List<Product> products;


    public Unit(String unit, String longUnit) {
        this.unit = unit;
        this.longUnit = longUnit;
    }
}