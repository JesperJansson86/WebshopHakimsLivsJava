package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.OrderStatus;
import com.example.hakimlivs.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 13:07
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
@RestController
@RequestMapping(path = ("/api/orderstatus"))
public class OrderStatusController {
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @GetMapping(path = "/add")
    public String addOrderStatus(
            @RequestParam String orderStatus
    ){
        OrderStatus oStatus = new OrderStatus(orderStatus);

        orderStatusRepository.save(oStatus);
        return "\nOrderStatus has been added! ";
    }

    @GetMapping(path ="/byId")
    public OrderStatus getOrderStatusById(@RequestParam Long id){
        return orderStatusRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<OrderStatus> getAllOrderStatus(){
        return orderStatusRepository.findAll();
    }

}
