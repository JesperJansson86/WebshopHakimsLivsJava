package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Unit;
import com.example.hakimlivs.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 11:02
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
@RestController
@RequestMapping(path = ("/api/unit"))
public class UnitController {
    @Autowired
    UnitRepository unitRepository;

    @GetMapping(path = "/add")
    public String addUnit(
            @RequestParam String unit,
            @RequestParam String longUnit
    ){
        Unit u = new Unit();
        u.setUnit(unit);
        u.setLongUnit(longUnit);
        return String.format("%s %s has been added", unit, longUnit);
    }
    @GetMapping(path="/byId")
    public Unit getunitById(@RequestParam long id){ return unitRepository.findById(id).get();}

    @GetMapping(path="/all")
    public Iterable<Unit> getAllunit(){ return unitRepository.findAll();}
}
