package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn(name = "adressid", referencedColumnName = "id")
    private Address address;

    private String email;

    private String Password;

    private Boolean loyalCustomer;

    private String phoneNumber;

    private Boolean adminStatus;

    public Customer(String firstName, String lastName, Address address, String phoneNumber, String email, String password, Boolean loyalCustomer, Boolean adminStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.Password = password;
        this.loyalCustomer = loyalCustomer;
        this.adminStatus = adminStatus;
    }
}