package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:28
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
@NoArgsConstructor
public class Order_Contains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private Product product;

    private int productAmount;

}