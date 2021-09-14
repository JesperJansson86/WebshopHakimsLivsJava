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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/customer"))
public class CustomerController {

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

//    DEPRECATED!
//    @GetMapping(path = "/add")
//    public String addCustomer(
//            @RequestParam String firstname,
//            @RequestParam String lastname,
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam boolean loyalCustomer,
//            @RequestParam boolean adminStatus,
//            @RequestParam String address,
//            @RequestParam String areaCode,
//            @RequestParam String city
//    ) {
//        Customer customer = new Customer();
//        customer.setFirstName(firstname);
//        customer.setLastName(lastname);
//        customer.setEmail(email);
//        customer.setPassword(password);
//        customer.setLoyalCustomer(loyalCustomer);
//        customer.setAdminStatus(adminStatus);
//        Address tempAddress = new Address();
//        AreaCode tempAreaCode = new AreaCode();
//        City tempCity = new City();
//        if (cityRepository.findCityBycity(city) == null) {
//            tempCity.setCity(city);
//            cityRepository.save(tempCity);
//        }
//        if (areaCodeRepository.findAreaCodeByareaCode(areaCode) == null) {
//            tempAreaCode.setAreaCode(areaCode);
//            areaCodeRepository.save(tempAreaCode);
//        }
//        if (addressRepository.findAddressByAddress(address) == null) {
//            tempAddress.setAddress(address);
//            addressRepository.save(tempAddress);
//        }
//        customer.setAddress(addressRepository.findAddressByAddress(address));
//        tempCity = cityRepository.findCityBycity(city);
//        tempAreaCode = areaCodeRepository.findAreaCodeByareaCode(areaCode);
//        tempAddress = addressRepository.findAddressByAddress(address);
//        tempAreaCode.setCity(tempCity);
//        tempAddress.setAreaCode(tempAreaCode);
//        customer.setAddress(tempAddress);
//        cityRepository.save(tempCity);
//        areaCodeRepository.save(tempAreaCode);
//        addressRepository.save(tempAddress);
//        customerRepository.save(customer);
//
//        return String.format("Kunden %s %s has been added", firstname, lastname);
//    }

    @PostMapping("api/customer/add")
    public String addCustomerPost(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.addCustomer(customerDTO);

        return customerDTO.compareToCustomer(customer) ? "customer Added" : "customer not added";
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
