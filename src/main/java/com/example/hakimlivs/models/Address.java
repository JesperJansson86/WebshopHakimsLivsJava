package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "areacode_id")
    private AreaCode areaCode;

    public Address(String address, AreaCode areaCode) {
        this.address = address;
        this.areaCode = areaCode;
    }
}