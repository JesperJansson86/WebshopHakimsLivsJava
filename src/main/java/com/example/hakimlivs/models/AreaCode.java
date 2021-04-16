package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:16
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
public class AreaCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaCode;
    @NonNull
    private City city;
}