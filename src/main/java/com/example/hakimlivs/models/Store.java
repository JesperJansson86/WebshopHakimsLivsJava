package com.example.hakimlivs.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:33
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private String phone;
     
    private String email;
     
    private String openHours;

    @ManyToOne
    private Address address;

}