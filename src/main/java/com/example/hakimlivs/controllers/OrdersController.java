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
            @RequestParam String orderStatus,
            @RequestParam Long customerId,
            @RequestParam String deliveryType,
            @RequestParam double deliveryCost,
            @RequestParam Long addressId

            ) throws NotFoundException {

        LocalDate now = LocalDate.now();
        Address deliveryAddress;
        OrderStatus oStatus = new OrderStatus();
        DeliveryOption dOption = new DeliveryOption();
        Customer customer;

        if (addressRepository.findAddressById(addressId)==null){
            deliveryAddress = addressRepository.findAddressById(addressId);
        } else {
            deliveryAddress = null;
           // throw new NotFoundException(String.format("Address with id:%s was not found",addressId));
        }

        if(orderStatusRepository.findStatusByOrderStatus(orderStatus)==null){
            oStatus.setOrderStatus(orderStatus);
            orderStatusRepository.save(oStatus);
        } else {
            oStatus = orderStatusRepository.findStatusByOrderStatus(orderStatus);
        }

        if(customerRepository.findCustomerById(customerId)==null){
            customer = customerRepository.findCustomerById(customerId);
        } else {
            throw new NotFoundException(String.format("Customer with id:%s was not found",customerId));
        }

        if(deliveryOptionRepository.findOptionByDeliveryType(deliveryType)==null){
            dOption.setDeliveryType(deliveryType);
            dOption.setDeliveryCost(deliveryCost);
            deliveryOptionRepository.save(dOption);
        } else {
            dOption = deliveryOptionRepository.findOptionByDeliveryType(deliveryType);
        }

        Orders orders = new Orders(now,oStatus,customer,dOption,deliveryAddress);
        ordersRepository.save(orders);
        return "\nOrder added to " + customer.getFirstName() + " with delivery method " + deliveryType + " ";
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
    public String deleteOrdersById(@RequestParam long id) throws NotFoundException {
        if(ordersRepository.findById(id).isPresent()){
            ordersRepository.deleteById(id);
            return String.format("Order with id:%s has been deleted", id);
        } else throw new NotFoundException(String.format("Item by that id:%s was not found and therefore not deleted ",id));
    }

    @GetMapping(path="/all")
    public Iterable<Orders> getAllOrders() { return ordersRepository.findAll();}

}
