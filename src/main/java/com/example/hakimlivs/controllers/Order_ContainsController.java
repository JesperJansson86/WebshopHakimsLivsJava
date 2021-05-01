package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Order_Contains;
import com.example.hakimlivs.repositories.Order_ContainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/order_Contains"))
public class Order_ContainsController {

    @Autowired
    Order_ContainsRepository order_ContainsRepository;

    @GetMapping(path = "/add")
    public String addorder_Contains(
            @RequestParam String order_Contains
    ) {
        Order_Contains order_c = new Order_Contains();

        order_ContainsRepository.save(order_c);

        return "\norder_Contains has been added! ";
    }

    @GetMapping(path ="/byId")
    public Order_Contains getorder_ContainsById(@RequestParam Long id){
        return Order_ContainsRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Order_Contains> getAllorder_Contains() {
        return order_ContainsRepository.findAll();
    }

    @GetMapping(path = "/deleteById")
    public String deleteOrder_ContainsById(@RequestParam Long id) {
        order_ContainsRepository.deleteById(id);
        return String.format("Order_Contains with id: %s has been deleted", id);
    }
}
