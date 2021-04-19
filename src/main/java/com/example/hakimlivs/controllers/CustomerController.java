package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.models.City;
import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/customer"))
public class CustomerController {
@Autowired
    CustomerRepository customerRepository;
@Autowired
    AddressRepository addressrepo;
@Autowired
AddressRepository areaCodeRepository;

    @GetMapping(path = "/add")
    public String addCustomer(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam boolean loyalCustomer,
            @RequestParam boolean adminStatus,
            @RequestParam String address,
            @RequestParam String areaCode,
            @RequestParam String city
    ){
        Customer customer = new Customer();
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setLoyalCustomer(loyalCustomer);
        customer.setAdminStatus(adminStatus);
        Address a = new Address();
        a.setAddress(address);
        AreaCode acode = new AreaCode();
        acode.setAreaCode(areaCode);
        City c   = new City();
        c.setCity(city);
        acode.setCity(c);
        a.setAreaCode(acode);


        customer.setAddress(a);
       
        customerRepository.save(customer);
        addressrepo.save(a);
        
        return String.format("Kunden %s %s has been added", firstname, lastname);
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
