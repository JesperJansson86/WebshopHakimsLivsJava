package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David Hedman <br>
 * Date: 2021-04-19 <br>
 * Time: 09:30 <br>
 * Project: hakimLivs <br>
 * Copyright: Nackademin <br>
 */
public interface BrandRepository extends CrudRepository<Brand, Long> {

    List<Brand> findByName(String name);
}
