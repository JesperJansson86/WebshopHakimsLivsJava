package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 10:00
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

   OrderStatus findStatusByOrderStatus(String orderStatus);
}
