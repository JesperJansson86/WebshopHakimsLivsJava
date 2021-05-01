package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import com.example.hakimlivs.repositories.CityRepository;
import com.example.hakimlivs.repositories.StoreRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/store"))
public class StoreController {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;
    @Autowired
    CityRepository cityRepository;

    @GetMapping(path = "/add")
    public String addStore(
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String openHours,
            @RequestParam String address,
            @RequestParam String areaCode,
            @RequestParam String city
    ) {
        City c = new City(city);
        AreaCode ac = new AreaCode(areaCode, c);
        Address a = new Address(address, ac);
        Store store = new Store(phone, email, openHours, a);

        if (cityRepository.findCityBycity(city) == null) {
            c.setCity(city);
        }

        cityRepository.save(c);
        areaCodeRepository.save(ac);
        addressRepository.save(a);
        storeRepository.save(store);

        return "the Store has been added!";
    }

    @GetMapping(path = "/findById")
    public Store getStoreById(@RequestParam Long id) throws NotFoundException {
        if (storeRepository.findById(id).isPresent()) {
            return storeRepository.findById(id).get();
        } else {
            throw new NotFoundException("Item by that id was not found");
        }
    }

    @GetMapping(path = "/deleteById")
    public String deleteStoreById(@RequestParam long id) {
        if (storeRepository.findById(id).isPresent()) {
            storeRepository.deleteById(id);
            return String.format("Store with id:%s has been deleted", id);
        } else return String.format("Store by id:%s did not exist and was therefore not deleted", id);
    }

    @GetMapping(path = "/all")
    public Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }
}