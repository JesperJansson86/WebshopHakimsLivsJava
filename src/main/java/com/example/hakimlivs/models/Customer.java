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
    @JoinColumn(name = "adressid",referencedColumnName = "id")
    private Address address;

    private String email;

    private String Password;

    private Boolean loyalCustomer;

    private String phoneNumber;

    private Boolean adminStatus;

    public Customer(String firstName, String lastName, Address address, String email, String password, Boolean loyalCustomer, Boolean adminStatus,String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.Password = password;
        this.loyalCustomer = loyalCustomer;
        this.phoneNumber = phoneNumber;
        this.adminStatus = adminStatus;
    }
}