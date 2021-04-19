package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 13:23
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
@RestController
@RequestMapping(path = ("/api/orders"))
public class OrdersController {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    AreaCodeRepository areaCodeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DeliveryOptionRepository deliveryOptionRepository;

    @GetMapping(path = "/add")
    public String addOrders(
            @RequestParam LocalDate orderDate,
            @RequestParam String orderStatus,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Boolean loyalCustomer,
            @RequestParam Boolean adminStatus,
            @RequestParam String deliveryType,
            @RequestParam double deliveryCost,
            @RequestParam String customerAddress,
            @RequestParam String customerAreaCode,
            @RequestParam String customerCity,
            @RequestParam String address,
            @RequestParam String areaCode,
            @RequestParam String city
            ){
        City c = new City(city);
        AreaCode ac = new AreaCode(areaCode,c);
        Address a = new Address(address,ac);

        City cc = new City(customerCity);
        AreaCode cac = new AreaCode(customerAreaCode,cc);
        Address ca = new Address(customerAddress,cac);

        OrderStatus oStatus = new OrderStatus(orderStatus);

        Customer customer = new Customer(firstName,lastName,ca,email,password,loyalCustomer,adminStatus);

        DeliveryOption dOption = new DeliveryOption(deliveryType,deliveryCost);

        Orders orders = new Orders(orderDate,oStatus,customer,dOption,a);

        ordersRepository.save(orders);

        orderStatusRepository.save(oStatus);

        customerRepository.save(customer);

        deliveryOptionRepository.save(dOption);

        cityRepository.save(c);
        cityRepository.save(cc);
        areaCodeRepository.save(ac);
        areaCodeRepository.save(cac);
        addressRepository.save(a);
        addressRepository.save(ca);

        ordersRepository.save(orders);

        return "\nOrder added to " + firstName + " with delivery method " + deliveryType + " ";
    }


    @GetMapping(path="/byId")
    public Orders getOrdersById(@RequestParam long id){ return ordersRepository.findById(id).get();}

    @GetMapping(path="/all")
    public Iterable<Orders> getAllOrders() { return ordersRepository.findAll();}

}
