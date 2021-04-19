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
        try {
            if(c.getCategory() == null || c.getId() == null)
                throw new IllegalArgumentException("Missing parameter in object");
            if(categoryRepository.existsById(c.getId())){
                Category cExisting = categoryRepository.findById(c.getId()).get();
                cExisting.setCategory(c.getCategory());
                categoryRepository.save(cExisting);
                return new Message(true, String.format("%s updated: ID already exists.", c.getCategory()));
            } else {
                categoryRepository.save(c);
                return new Message(true, String.format("%s created: ID not found.", c.getCategory()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(false, "Error when processing.");
        }
    }
}
