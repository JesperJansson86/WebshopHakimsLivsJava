package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Category;
import com.example.hakimlivs.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/category"))
public class CategoryController {
    CategoryRepository categoryRepository;

    @GetMapping(path = "/all")
    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/add")
    public String addCategory(@RequestParam String name){
        Category c = new Category();
        c.setCategory(name);
        categoryRepository.save(c);
        return String.format("%s added", c.getCategory());
    }
}
