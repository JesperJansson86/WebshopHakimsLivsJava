package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.models.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
    public City findCityBycity(String a);
}
