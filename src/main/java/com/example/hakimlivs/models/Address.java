package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:19
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "areacodeid")
    private AreaCode areaCode;

    public Address(String address, AreaCode areaCode) {
        this.address = address;
        this.areaCode = areaCode;
    }
}