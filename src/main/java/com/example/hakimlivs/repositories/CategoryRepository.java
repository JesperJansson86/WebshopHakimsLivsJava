package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Brand;
import com.example.hakimlivs.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    public Optional<Category> findCategoryBycategory(String category);

   // List<Category> findByName(String name);
}