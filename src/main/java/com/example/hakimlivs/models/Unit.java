package com.example.hakimlivs.models;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:08
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    private String unit;
     
    private String longUnit;

}