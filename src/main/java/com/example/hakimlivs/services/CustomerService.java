package com.example.hakimlivs.services;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.models.City;
import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.models.DTO.CustomerDTO;
import com.example.hakimlivs.rabbitmq.RabbitSend;
import com.example.hakimlivs.repositories.AddressRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import com.example.hakimlivs.repositories.CityRepository;
import com.example.hakimlivs.repositories.CustomerRepository;
import com.example.hakimlivs.security.SecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    RabbitSend rabbitSend;

    public Customer addCustomer(CustomerDTO customerDTO) {
        //MAPPING
        TextValidator.validateCustomer(customerDTO);

        if(findCustomerByEmail(customerDTO.getEmail()) != null) throw new IllegalArgumentException("Email is in use");

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstname());
        customer.setLastName(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhone());
        customer.setPassword(SecurityConfiguration.passwordEncoder().encode(customerDTO.getPassword()));
        customer.setLoyalCustomer(false);
        customer.setAdminStatus(false);

        //Skapar tempvariabler
        Address tempAddress = new Address();
        AreaCode tempAreaCode = new AreaCode();
        City tempCity = new City();

        //Kontrollera om stad finns, annars skapa ny
        if (cityRepository.findCityBycity(customerDTO.getCity()) == null) {
            tempCity.setCity(customerDTO.getCity());
            cityRepository.save(tempCity);
        }

        //Ta bort tomma tecken i AreaCode
        customerDTO.setAreaCode(customerDTO.getAreaCode().replace(" ",""));
        customerDTO.setAreaCode(customerDTO.getAreaCode().trim());


        //Kontrollera om area code finns, annars skapa ny.
        if (areaCodeRepository.findAreaCodeByareaCode(customerDTO.getAreaCode()) == null) {
            tempAreaCode.setAreaCode(customerDTO.getAreaCode());
            areaCodeRepository.save(tempAreaCode);
        }

        //Kontrollera om adress finns, annars spara ny.
        if (addressRepository.findAddressByAddress(customerDTO.getAddress()) == null) {
            tempAddress.setAddress(customerDTO.getAddress());
            addressRepository.save(tempAddress);
        }

        customer.setAddress(addressRepository.findAddressByAddress(customerDTO.getAddress()));

        tempCity = cityRepository.findCityBycity(customerDTO.getCity());
        tempAreaCode = areaCodeRepository.findAreaCodeByareaCode(customerDTO.getAreaCode());
        tempAddress = addressRepository.findAddressByAddress(customerDTO.getAddress());
        tempAreaCode.setCity(tempCity);
        tempAddress.setAreaCode(tempAreaCode);
        customer.setAddress(tempAddress);
        cityRepository.save(tempCity);
        areaCodeRepository.save(tempAreaCode);
        addressRepository.save(tempAddress);
        customerRepository.save(customer);
        rabbitSend.sendmail(customer.getEmail(),"newaccount");

        return customer;
    }

    public Customer findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
}
