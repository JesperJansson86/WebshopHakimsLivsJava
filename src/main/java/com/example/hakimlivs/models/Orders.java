package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:25
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private LocalDate orderDate;

    @ManyToOne
    private OrderStatus orderStatus;
    @ManyToOne
    private Customer customer;

}
