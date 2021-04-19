package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David Hedman <br>
 * Date: 2021-04-19 <br>
 * Time: 09:38 <br>
 * Project: hakimLivs <br>
 * Copyright: Nackademin <br>
 */
@RestController
@RequestMapping(path = ("/api/product"))
public class ProductController {

    @GetMapping(path = "/add")
    public String addProduct(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int inventory,
            @RequestParam int quantity,
            @RequestParam int size,
            @RequestParam Brand brand,
            @RequestParam Category category,
            @RequestParam Unit unit,
            @RequestParam boolean visibility
    ){
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setInventory(inventory);
        p.setQuantity(quantity);


        return String.format("%s has been added", title);
    }
}
