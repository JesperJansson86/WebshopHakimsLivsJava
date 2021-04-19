package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private String phone;
     
    private String email;
     
    private String openHours;

    @ManyToOne
    private Address address;

    public Store(String phone, String email, String openHours, Address address) {
        this.phone = phone;
        this.email = email;
        this.openHours = openHours;
        this.address = address;
    }
}