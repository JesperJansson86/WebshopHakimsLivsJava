package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Long> {

    public Optional<Brand> findByBrand(String brand);

    public boolean existsByBrand(String brand);
}
