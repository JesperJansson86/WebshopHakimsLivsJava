package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.DeliveryOption;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryOptionRepository extends CrudRepository<DeliveryOption, Long> {
    DeliveryOption findOptionByDeliveryType(String deliveryType);
}
