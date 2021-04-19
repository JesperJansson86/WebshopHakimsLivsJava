package com.example.hakimlivs.models;

import lombok.Data;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Address address;

    private String email;

    private String Password;

    private Boolean loyalCustomer;

    private Boolean adminStatus;
}