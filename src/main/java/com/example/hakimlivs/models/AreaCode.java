package com.example.hakimlivs.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class AreaCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaCode;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "city_id")
    private City city;
}