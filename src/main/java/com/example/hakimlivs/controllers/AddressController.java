package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/address"))
public class AddressController {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;

    @GetMapping(path = "/add")
    public String addaddress(
            @RequestParam String inputAddress,
            @RequestParam String areaCode
    ) {

        Address address = new Address();
        address.setAddress(inputAddress);
        address.setAreaCode(areaCodeRepository.findAreaCodeByareaCode(areaCode));
        return String.format("%s %s has been added", inputAddress, areaCode);
    }

    @GetMapping(path = "/findById")
    public Address getaddressById(@RequestParam Long id) {
        return addressRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Address> getAlladdresss() {
        return addressRepository.findAll();
    }

    @GetMapping(path = "/deleteById")
    public String deleteAddressById(@RequestParam Long id) {
        addressRepository.deleteById(id);
        return String.format("Address with id: %s has been deleted", id);
    }
}