package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 15:49
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    public City(String city) {
        this.city = city;
    }
}