package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Brand;
import com.example.hakimlivs.models.Unit;
import com.example.hakimlivs.repositories.BrandRepository;
import com.example.hakimlivs.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hanna Edlund
 * Date: 2021-04-20
 * Time: 09:32
 * Project: Java
 */
@RestController
@RequestMapping(path = ("/api/brand"))
public class BrandController {
    @Autowired
    BrandRepository brandRepo;

    @GetMapping(path = "/add")
    public String addBrand(
            @RequestParam String brand
    ){
        Brand b = new Brand();
        b.setBrand(brand);

        brandRepo.save(b);
        return String.format("%s has been added", brand);
    }
    @GetMapping(path="/byId")
    public Brand getBrandById(@RequestParam long id){ return brandRepo.findById(id).get();}

    @GetMapping(path="/all")
    public Iterable<Brand> getAllBrand(){ return brandRepo.findAll();}
}
