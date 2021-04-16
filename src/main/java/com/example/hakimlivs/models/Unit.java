package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:08
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
public class Unit {

    private int id;
    @NonNull
    private String unit;
    @NonNull
    private String longUnit;

}