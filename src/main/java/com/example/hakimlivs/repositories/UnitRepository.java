package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Category;
import com.example.hakimlivs.models.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 10:02
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface UnitRepository extends CrudRepository<Unit, Long> {
    public Optional<Unit> findByUnit(String unit);

    public boolean existsByUnit (String unit);
}
