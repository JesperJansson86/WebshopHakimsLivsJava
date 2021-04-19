package com.example.hakimlivs.controllers;


import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.repositories.AddressRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = ("/api/address"))
public class AddressController {

        AddressRepository addressRepository;

        @GetMapping(path = "/add")
        public String addaddress(
                @RequestParam String address,
                @RequestParam AreaCode areaCode
        ){

            Address a = new Address();
            a.setAddress(address);
            a.setAreaCode(areaCode);
            return String.format("%s %s has been added", address, areaCode);
        }

        @GetMapping(path ="/byId")
        public Address getaddressById(@RequestParam Long id){
            return addressRepository.findById(id).get();
        }

        @GetMapping(path = "/all")
        public Iterable<Address> getAlladdresss(){
            return addressRepository.findAll();
        }

    }


