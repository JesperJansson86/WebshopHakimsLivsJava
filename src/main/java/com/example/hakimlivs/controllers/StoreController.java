package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 11:13
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
@RestController
@RequestMapping(path = ("/api/store"))
public class StoreController {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressRepository areaCodeRepository;

    @GetMapping(path = "/add")
    public String addStore(
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String openHours,
            @RequestParam String address,
            @RequestParam String areaCode,
            @RequestParam String city
    ){
       City c = new City(city);
       AreaCode ac = new AreaCode(areaCode,c);
       Address a = new Address(address,ac);
       Store store = new Store(phone,email,openHours,a);
       storeRepository.save(store);
       addressRepository.save(a);
       return "the Store has been added!";
    }

    @GetMapping(path ="/byId")
    public Store getStoreById(@RequestParam Long id){
        return storeRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Store> getAllStores(){
        return storeRepository.findAll();
    }

}
