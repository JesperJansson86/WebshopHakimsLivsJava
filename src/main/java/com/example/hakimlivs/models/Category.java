package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:11
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
@Data
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
}