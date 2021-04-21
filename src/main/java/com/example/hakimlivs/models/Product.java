package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Lukas Aronsson
 * Date: 07/04/2021
 * Time: 11:51
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 */

/**
 * Objekt representation av Product tabellen i från databasen
 */
@Data
@Entity
@NoArgsConstructor
public class Product {

    /**
     * idn av produkten (primary key från databasen)
     * Om värdet inte sätts så är dens standard värdet 0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * namnet på produkten
     * Om värdet inte sätts så är dens standard värdet ""
     */
     
    private String title = "";

    /**
     * beskrivning av produkten
     * Om värdet inte sätts så är dens standard värdet ""
     */
     
    private String description = "";

    /**
     * Priset av produkten
     * Om värdet inte sätts så är dens standard värdet 0
     */
    private double price = 0;

    /**
     * Lagerstatus av produkten
     * Om värdet inte sätts så är dens standard värdet 0
     */
    private int inventory = 0;

    /**
     * Antal av items i produkten
     * Om värdet inte sätts så är dens standard värdet 0
     */
    private int quantity = 0;

    /**
     * Storeleks typ av produkten
     * Om värdet inte sätts så är dens standard värdet 0
     */
    private double size = 0.0;

    /**
     * Märket som har skapat eller paketerat produkten
     */

    @ManyToOne (cascade = {CascadeType.ALL}) // DETACH
    @JoinColumn(name = "brand_id")
    private Brand brand;

    /**
     * Kategorin som produkten är en del av
     */

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Enheten som produkten är räknad från
     */

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany (cascade = {CascadeType.ALL})
    @JoinColumn(referencedColumnName ="product_id")
    private List<Image> imageList;

    /**
     * Boolean som sätter om produkten ska vara synlig eller inte
     * Om värdet inte sätts så är dens standard värdet false
     */
    private boolean visibility = false;


    public Product(String title, String description, double price, int inventory, int quantity, int size, Brand brand, Category category, Unit unit, boolean visibility) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.quantity = quantity;
        this.size = size;
        this.brand = brand;
        this.category = category;
        this.unit = unit;
        this.visibility = visibility;
    }
}
