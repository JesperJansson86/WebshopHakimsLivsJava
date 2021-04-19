package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.BrandRepository;
import com.example.hakimlivs.repositories.CategoryRepository;
import com.example.hakimlivs.repositories.ProductRepository;
import com.example.hakimlivs.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BrandRepository brandRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private UnitRepository unitRepo;



    @GetMapping(path = "/add")
    public String addProduct(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int inventory,
            @RequestParam int quantity,
            @RequestParam int size,
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam Long unit,
            @RequestParam boolean visibility
    ){
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setInventory(inventory);
        p.setQuantity(quantity);
        p.setSize(size);

        if(brand.equals(brandRepo.findByBrand(brand).get().getBrand())){
            p.setBrand(brandRepo.findByBrand(brand).get());
        }
        else{
                    Brand b = new Brand();
                    b.setBrand(brand);
                    p.setBrand(b);
        }

        if(category.equals(categoryRepo.findCategoryBycategory(category).get().getCategory())){
            p.setCategory(categoryRepo.findCategoryBycategory(category).get());
        }
        else{
            Category c = new Category();
            c.setCategory(category);
            p.setCategory(c);
        }

        p.setUnit(unitRepo.findById(unit).get());
        p.setVisibility(visibility);

        return String.format("%s has been added", title);

    }

    @GetMapping(path ="/byId")
    public Product getProductById(@RequestParam Long id){
        return productRepo.findById(id).get();
    }

    @GetMapping(path ="/deleteById")
    public String deleteProductById(@RequestParam Long id){
        productRepo.deleteById(id);

        return String.format("Product with id:%s has been deleted", id);
    }

    @GetMapping(path = "/all")
    public Iterable<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product p){

        if(p.getId().equals(getProductById(p.getId()).getId())){
            addProduct(p.getTitle(), p.getDescription(), p.getPrice(), p.getInventory(), p.getQuantity(), p.getSize(), p.getBrand().getBrand(), p.getCategory().getCategory(), p.getUnit().getId(), p.isVisibility());
            return "Product created";
        }
        else{
            Product updateP = getProductById(p.getId());
            updateP.setTitle(p.getTitle());
            updateP.setDescription(p.getDescription());
            updateP.setInventory(p.getInventory());
            updateP.setQuantity(p.getQuantity());
            updateP.setSize(p.getSize());
            updateP.setBrand(brandRepo.findById(p.getBrand().getId()).get());
            updateP.setCategory(categoryRepo.findById(p.getCategory().getId()).get());
            updateP.setUnit(unitRepo.findById(p.getUnit().getId()).get());
            updateP.setVisibility(p.isVisibility());

            return "Product updated";
        }

    }

}
