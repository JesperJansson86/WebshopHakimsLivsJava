package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/customer"))
public class CustomerController {

    CustomerRepository customerRepository;
    AddressRepository addressrepo;

    @GetMapping(path = "/add")
    public String addCustomer(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam boolean loyalCustomer,
            @RequestParam boolean adminStatus,
            @RequestParam Long address
    ){
        Customer c = new Customer();
        c.setFirstName(firstname);
        c.setLastName(lastname);
        c.setEmail(email);
        c.setPassword(password);
        c.setLoyalCustomer(loyalCustomer);
        c.setAdminStatus(adminStatus);
        c.setAddress(addressrepo.findById(address).get());
        return String.format("%s %s has been added", firstname, lastname);
    }

    @GetMapping(path ="/byId")
    public Customer getCustomerById(@RequestParam Long id){
        return customerRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

}
