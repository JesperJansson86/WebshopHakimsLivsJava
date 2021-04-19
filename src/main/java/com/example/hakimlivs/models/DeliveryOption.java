package com.example.hakimlivs.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 10:16
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/

@Data
@Entity
public class DeliveryOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * typen av delivery
     */
    private String deliveryType;

    /**
     * Konstnaden av att skicka ordern till kunden
     * om kunden hämntar från butiken så är kostnaden 0
     */
    private double deliveryCost;

}
