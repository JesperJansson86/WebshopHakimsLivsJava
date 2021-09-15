package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.models.City;
import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.models.DTO.CustomerDTO;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import com.example.hakimlivs.repositories.CityRepository;
import com.example.hakimlivs.repositories.CustomerRepository;
import com.example.hakimlivs.security.SecurityConfiguration;
import com.example.hakimlivs.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/customer"))
public class CustomerController {
    public static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;
    @Autowired
    CityRepository cityRepository;

    @PostMapping("/add")
    public ResponseEntity addCustomerPost(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.addCustomer(customerDTO);
            LOG.info("Customer added: " + customerDTO.getEmail());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            LOG.error("Error creating user: " + e.getMessage());
            return new ResponseEntity<>(
                    "Error: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/findById")
    public Customer getCustomerById(@RequestParam Long id) {
        return customerRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //    @GetMapping(path = "/deleteById")
    @DeleteMapping(path = "/deleteById")
    public String deleteCustomerById(@RequestParam Long id) {
        customerRepository.deleteById(id);
        return String.format("Customer with id: %s has been deleted", id);
    }
}
