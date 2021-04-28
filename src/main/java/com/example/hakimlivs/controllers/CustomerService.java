package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.models.City;
import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import com.example.hakimlivs.repositories.CityRepository;
import com.example.hakimlivs.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;
    @Autowired
    CityRepository cityRepository;

    public Customer addCustomer(
            String firstname,
            String lastname,
            String email,
            String password,
            boolean loyalCustomer,
            boolean adminStatus,
            String address,
            String areaCode,
            String city
    ) {
        Customer customer = new Customer();
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setLoyalCustomer(loyalCustomer);
        customer.setAdminStatus(adminStatus);
        Address tempAddress = new Address();
        AreaCode tempAreaCode = new AreaCode();
        City tempCity = new City();
        if (cityRepository.findCityBycity(city) == null) {
            tempCity.setCity(city);
            cityRepository.save(tempCity);
        }
        if (areaCodeRepository.findAreaCodeByareaCode(areaCode) == null) {
            tempAreaCode.setAreaCode(areaCode);
            areaCodeRepository.save(tempAreaCode);
        }
        if (addressRepository.findAddressByAddress(address) == null) {
            tempAddress.setAddress(address);
            addressRepository.save(tempAddress);
        }
        customer.setAddress(addressRepository.findAddressByAddress(address));
        tempCity = cityRepository.findCityBycity(city);
        tempAreaCode = areaCodeRepository.findAreaCodeByareaCode(areaCode);
        tempAddress = addressRepository.findAddressByAddress(address);
        tempAreaCode.setCity(tempCity);
        tempAddress.setAreaCode(tempAreaCode);
        customer.setAddress(tempAddress);
        cityRepository.save(tempCity);
        areaCodeRepository.save(tempAreaCode);
        addressRepository.save(tempAddress);
        customerRepository.save(customer);

        return customer;
    }
}
