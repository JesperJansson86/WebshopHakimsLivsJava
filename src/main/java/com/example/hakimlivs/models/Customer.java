package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:21
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Address address;
    @NonNull
    private String email;
    @NonNull
    private String Password;
    @NonNull
    private Boolean loyalCustomer;
    @NonNull
    private Boolean adminStatus;
}