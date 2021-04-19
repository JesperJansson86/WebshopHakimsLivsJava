package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.DeliveryOption;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 13:59
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface DeliveryOptionRepository extends CrudRepository<DeliveryOption, Long> {
}
