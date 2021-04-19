package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Brand;
import com.example.hakimlivs.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by David Hedman <br>
 * Date: 2021-04-19 <br>
 * Time: 09:30 <br>
 * Project: hakimLivs <br>
 * Copyright: Nackademin <br>
 */
public interface BrandRepository extends CrudRepository<Brand, Long> {

    public Optional<Brand> findByBrand(String brand);

    public boolean existsByBrand(String brand);
}
