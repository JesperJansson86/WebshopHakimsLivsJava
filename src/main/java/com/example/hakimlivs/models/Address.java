package com.example.hakimlivs.models;


import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:19
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
public class Address {

    @NonNull
    private int id;
    @NonNull
    private String address;
    @NonNull
    private AreaCode areaCode;

}