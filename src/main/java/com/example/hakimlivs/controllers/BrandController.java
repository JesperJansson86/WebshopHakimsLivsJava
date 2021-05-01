package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Brand;
import com.example.hakimlivs.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/brand"))
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    @GetMapping(path = "/add")
    public String addBrand(
            @RequestParam String brand
    ) {
        Brand b = new Brand();
        b.setBrand(brand);

        brandRepository.save(b);
        return String.format("%s has been added", brand);
    }
    @GetMapping(path="/byId")
    public Brand getBrandById(@RequestParam long id){ return brandRepo.findById(id).get();}

    @GetMapping(path = "/all")
    public Iterable<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @GetMapping(path = "/deleteById")
    public String deleteBrandById(@RequestParam Long id) {
        brandRepository.deleteById(id);
        return String.format("Brand with id: %s has been deleted", id);
    }
}
