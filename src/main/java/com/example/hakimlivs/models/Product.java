package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

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
public class Product {

    /**
     * idn av produkten (primary key från databasen)
     * Om värdet inte sätts så är dens standard värdet 0
     */
    private int id = 0;

    /**
     * namnet på produkten
     * Om värdet inte sätts så är dens standard värdet ""
     */
    @NonNull
    private String title = "";

    /**
     * beskrivning av produkten
     * Om värdet inte sätts så är dens standard värdet ""
     */
    @NonNull
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
    private int size = 0;

    /**
     * Märket som har skapat eller paketerat produkten
     */
    @NonNull
    private Brand brand;

    /**
     * Kategorin som produkten är en del av
     */
    @NonNull
    private Category category;

    /**
     * Enheten som produkten är räknad från
     */
    @NonNull
    private Unit unit;

    /**
     * Boolean som sätter om produkten ska vara synlig eller inte
     * Om värdet inte sätts så är dens standard värdet false
     */
    private boolean visibility = false;


}
