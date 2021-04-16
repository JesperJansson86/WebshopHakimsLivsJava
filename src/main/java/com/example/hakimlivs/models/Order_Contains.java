package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:28
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
public class Order_Contains {

    private int id;
    @NonNull
    private Orders order;
    @NonNull
    private Product product;
    private int productAmount;

}