package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public boolean existsByimageList(String s);
}
