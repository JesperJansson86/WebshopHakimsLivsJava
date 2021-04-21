package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import javassist.NotFoundException;
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
            @RequestParam String address,
            @RequestParam String areaCode,
            @RequestParam String city,
            @RequestParam String phoneNumber
            ){

        City c = new City();
        AreaCode ac = new AreaCode();
        Address a = new Address();
        OrderStatus oStatus = new OrderStatus();
        DeliveryOption dOption = new DeliveryOption();
        Customer customer = new Customer(firstName,lastName,a,phoneNumber,email,password,loyalCustomer,adminStatus);

        if(cityRepository.findCityBycity(city)==null){
            c.setCity(city);
            cityRepository.save(c);
        } else{
          c = cityRepository.findCityBycity(city);
        }

        if (areaCodeRepository.findAreaCodeByareaCode(areaCode)==null){
            ac.setAreaCode(areaCode);
            ac.setCity(c);
            areaCodeRepository.save(ac);
        } else{
            ac = areaCodeRepository.findAreaCodeByareaCode(areaCode);
        }

        if (addressRepository.findAddressByAddress(address)==null){
            a.setAddress(address);
            a.setAreaCode(ac);
            addressRepository.save(a);
        } else {
            a = addressRepository.findAddressByAddress(address);
        }

        if(orderStatusRepository.findStatusByOrderStatus(orderStatus)==null){
            oStatus.setOrderStatus(orderStatus);
            orderStatusRepository.save(oStatus);
        } else {
            oStatus = orderStatusRepository.findStatusByOrderStatus(orderStatus);
        }

        if(customerRepository.findCustomerByEmail(email)==null){
            customerRepository.save(customer);
        } else {
            customer = customerRepository.findCustomerByEmail(email);
        }

        if(deliveryOptionRepository.findOptionByDeliveryType(deliveryType)==null){
            dOption.setDeliveryType(deliveryType);
            dOption.setDeliveryCost(deliveryCost);
            deliveryOptionRepository.save(dOption);
        } else {
            dOption = deliveryOptionRepository.findOptionByDeliveryType(deliveryType);
        }

        Orders orders = new Orders(orderDate,oStatus,customer,dOption,a);
        ordersRepository.save(orders);
        return "\nOrder added to " + firstName + " with delivery method " + deliveryType + " ";
    }


    @GetMapping(path="/byId")
    public Orders getOrdersById(@RequestParam long id) throws NotFoundException {
        if(ordersRepository.findById(id).isPresent()){
            return ordersRepository.findById(id).get();
        } else {
            throw new NotFoundException(String.format("Item by that id:%s was not found",id));
        }
    }

    @GetMapping(path = "/deleteById")
    public String deleteOrdersById(@RequestParam long id){
        if(ordersRepository.findById(id).isPresent()){
            ordersRepository.deleteById(id);
            return String.format("Order with id:%s has been deleted", id);
        } else return String.format("Order by that id:%s did not exist and was therefore not deleted",id);
    }

    @GetMapping(path="/all")
    public Iterable<Orders> getAllOrders() { return ordersRepository.findAll();}

}
