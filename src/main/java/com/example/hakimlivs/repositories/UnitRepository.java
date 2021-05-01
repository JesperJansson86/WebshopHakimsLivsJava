package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    Optional<Unit> findByUnit(String unit);

    Optional<Unit> findByLongUnit(String longUnit);

    boolean existsByUnit(String unit);

    boolean existsByLongUnit(String longUnit);
}
