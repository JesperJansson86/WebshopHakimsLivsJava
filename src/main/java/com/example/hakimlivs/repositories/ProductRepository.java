package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {

}
