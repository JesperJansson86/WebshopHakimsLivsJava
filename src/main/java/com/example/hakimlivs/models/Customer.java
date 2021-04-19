package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:21
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "adress_id",referencedColumnName = "id")
    private Address address;

    private String email;

    private String Password;

    private Boolean loyalCustomer;

    private Boolean adminStatus;
}