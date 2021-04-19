package com.example.hakimlivs.models;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "deliveryoption_id",referencedColumnName = "id")
    private DeliveryOption deliveryOption;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "adress_id",referencedColumnName = "id")
    private Address deliveryAddress;

}
