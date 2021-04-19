package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Category;
import com.example.hakimlivs.models.Message;
import com.example.hakimlivs.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/category"))
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(path = "/all")
    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/add")
    public String addCategory(@RequestParam String category){
        if(categoryRepository.findCategoryBycategory(category).isPresent()){
            return String.format("%s finns redan i databas.", category);
        } else {
            Category c = new Category();
            c.setCategory(category);
            categoryRepository.save(c);
            return String.format("%s added", c.getCategory());
        }
    }

    @PostMapping("/uprest")
    public Message uprestCategory(@RequestBody Category c){
        return new Message(false, String.format("%s uppdaterad", c.getCategory()));
    }
}
