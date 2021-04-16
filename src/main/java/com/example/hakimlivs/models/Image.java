package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:31
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
public class Image {

    private int id;
    @NonNull
    private String image;
    @NonNull
    private Product product;
}