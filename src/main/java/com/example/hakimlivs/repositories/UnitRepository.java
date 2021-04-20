package com.example.hakimlivs.repositories;

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
    Optional<Unit> findByUnit(String unit);

    Optional<Unit> findByLongUnit(String longUnit);

    boolean existsByUnit (String unit);

    boolean existsByLongUnit(String longUnit);
}
